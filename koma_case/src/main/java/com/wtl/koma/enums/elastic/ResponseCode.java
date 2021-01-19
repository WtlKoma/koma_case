package com.wtl.koma.enums.elastic;

/**
 * @Description
 * @Author WTL
 * @Date 2021/1/7
 */
public enum ResponseCode {

    DUPLICATEKEY_ERROR_CODE("1001", "索引已存在"),
    ERROR("5001", "执行错误"),
    RESOURCE_NOT_EXIST("1002", "索引不存在"),
    NETWORK_ERROR("4001", "网络连接出错"),
    PARAM_ERROR_CODE("1003", "索引为空，不允许提交");

    private String code;

    private String msg;

    private ResponseCode (String code, String msg){
        this.code = code;
        this .msg = msg;
    }

    public String getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }

}
