package com.wtl.koma.pojo.elastic;

import lombok.Data;

/**
 * @Description
 * @Author WTL
 * @Date 2021/1/7
 */
@Data
public class ResponseResult {

    private Boolean status = true;

    private String code = "200";

    private String msg = "成功";

    private Object data;

}
