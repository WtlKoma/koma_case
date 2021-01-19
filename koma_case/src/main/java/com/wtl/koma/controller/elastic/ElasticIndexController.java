package com.wtl.koma.controller.elastic;

import com.alibaba.fastjson.JSON;
import com.wtl.koma.enums.elastic.ResponseCode;
import com.wtl.koma.pojo.elastic.*;
import com.wtl.koma.service.elastic.BaseElasticService;
import com.wtl.koma.utils.ElasticUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @ClassName ElasticIndexController
 * @Description ElasticSearch索引的基本管理，提供对外查询、删除和新增功能
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2019/11/19 11:22
 * @Version 1.0.0
*/
@Slf4j
@RequestMapping("/elastic")
@RestController
public class ElasticIndexController {

    @Autowired
    BaseElasticService baseElasticService;

    @GetMapping(value = "/")
    public ResponseResult index(String index){
        return new ResponseResult();
    }

    /**
     * @Description 创建Elastic索引
     * @param idxVo
     * @throws
     * @date 2019/11/19 11:07
     */
    @PostMapping(value = "/createIndex")
    public ResponseResult createIndex(@RequestBody IdxReq idxVo){
        ResponseResult response = new ResponseResult();
        try {
            //索引不存在，再创建，否则不允许创建
            if(!baseElasticService.isExistsIndex(idxVo.getIdxName())){
                String idxSql = JSON.toJSONString(idxVo.getIdxSql());
                log.warn(" idxName={}, idxSql={}",idxVo.getIdxName(),idxSql);
                baseElasticService.createIndex(idxVo.getIdxName(),idxSql);
            } else{
                response.setStatus(false);
                response.setCode(ResponseCode.DUPLICATEKEY_ERROR_CODE.getCode());
                response.setMsg("索引已经存在，不允许创建");
            }
        } catch (Exception e) {
            response.setStatus(false);
            response.setCode(ResponseCode.ERROR.getCode());
            response.setMsg(ResponseCode.ERROR.getMsg());
        }
        return response;
    }


    /**
     * @Description 判断索引是否存在；存在-TRUE，否则-FALSE
     * @param index
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @date 2019/11/19 18:48
     */
    @GetMapping(value = "/exist/{index}")
    public ResponseResult indexExist(@PathVariable(value = "index") String index){

        ResponseResult response = new ResponseResult();
        try {
            if(!baseElasticService.isExistsIndex(index)){
                log.error("index={},不存在",index);
                response.setCode(ResponseCode.RESOURCE_NOT_EXIST.getCode());
                response.setMsg(ResponseCode.RESOURCE_NOT_EXIST.getMsg());
            } else {
                response.setMsg(" 索引已经存在, " + index);
            }
        } catch (Exception e) {
            response.setCode(ResponseCode.NETWORK_ERROR.getCode());
            response.setMsg(" 调用ElasticSearch 失败！");
            response.setStatus(false);
        }
        return response;
    }

    @GetMapping(value = "/del/{index}")
    public ResponseResult indexDel(@PathVariable(value = "index") String index){
        ResponseResult response = new ResponseResult();
        try {
//            baseElasticService.deleteIndex(index);
        } catch (Exception e) {
            response.setCode(ResponseCode.NETWORK_ERROR.getCode());
            response.setMsg(" 调用ElasticSearch 失败！");
            response.setStatus(false);
        }
        return response;
    }

    /**************************web端请求***********************/

    /**
     * @Description 新增数据
     * @param elasticDataVo
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @date 2019/11/20 17:10
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseResult add(@RequestBody ElasticDataReq elasticDataVo){
        ResponseResult response = new ResponseResult();
        try {
            if(!StringUtils.isNotEmpty(elasticDataVo.getIdxName())){
                response.setCode(ResponseCode.PARAM_ERROR_CODE.getCode());
                response.setMsg("索引为空，不允许提交");
                response.setStatus(false);
                log.warn("索引为空");
                return response;
            }
            ElasticEntity elasticEntity = new ElasticEntity();
            elasticEntity.setId(elasticDataVo.getElasticEntity().getId());
            elasticEntity.setData(elasticDataVo.getElasticEntity().getData());

            baseElasticService.insertOrUpdateOne(elasticDataVo.getIdxName(), elasticEntity);

        } catch (Exception e) {
            response.setCode(ResponseCode.ERROR.getCode());
            response.setMsg("服务忙，请稍后再试");
            response.setStatus(false);
            log.error("插入数据异常，metadataVo={},异常信息={}", elasticDataVo.toString(),e.getMessage());
        }
        return response;
    }

    /**
     * @Description
     * @param queryVo 查询实体对象
     * @throws
     * @date 2019/11/21 9:31
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseResult get(@RequestBody QueryReq queryVo){

        ResponseResult response = new ResponseResult();

        if(!StringUtils.isNotEmpty(queryVo.getIdxName())){
            response.setCode(ResponseCode.PARAM_ERROR_CODE.getCode());
            response.setMsg("索引为空，不允许提交");
            response.setStatus(false);
            log.warn("索引为空");
            return response;
        }

        try {
            Class<?> clazz = ElasticUtil.getClazz(queryVo.getClassName());
            Map<String,Object> params = queryVo.getQuery().get("match");
            Set<String> keys = params.keySet();
            MatchQueryBuilder queryBuilders=null;
            for(String ke:keys){
                queryBuilders = QueryBuilders.matchQuery(ke, params.get(ke));
            }
            if(null!=queryBuilders){
                SearchSourceBuilder searchSourceBuilder = ElasticUtil.initSearchSourceBuilder(queryBuilders);
                List<?> data = baseElasticService.search(queryVo.getIdxName(),searchSourceBuilder,clazz);
                response.setData(data);
            }
        } catch (Exception e) {
            response.setCode(ResponseCode.ERROR.getCode());
            response.setMsg("服务忙，请稍后再试");
            response.setStatus(false);
            log.error("查询数据异常，metadataVo={},异常信息={}", queryVo.toString(),e.getMessage());
        }
        return response;
    }

}