package com.wtl.koma.crypto.enums;

/**
 * 加密大类型
 */
public enum CryptoEnum {

    SYMMETRIC(10, "对称加密算法(DES,AES,DESede)"),
    ASYMMETRIC(20, "非对称加密算法(RSA)"),
    ;

    private int type;

    private String desc;

    CryptoEnum(int type, String desc){
        this.type = type;
        this.desc = desc;
    }
}
