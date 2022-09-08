package com.wtl.koma.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 */
@WebService
public interface SendService {

    /**
     * 数据传输
     *
     * @param sysCode   编码
     * @param nodeCodes 节点编码
     * @param dataTitle 标题
     * @param dataType  数据类型
     * @param data      数据
     * @return 数据
     */
    @WebMethod(operationName = "sendData")
    String sendData(@WebParam(name = "sysCode") String sysCode,
                    @WebParam(name = "nodeCodes") String nodeCodes,
                    @WebParam(name = "dataTitle") String dataTitle,
                    @WebParam(name = "dataType") String dataType,
                    @WebParam(name = "dataContent") String data
    );

}
