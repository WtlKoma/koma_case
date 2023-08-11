package com.wtl.koma.crypto.enums;


import com.wtl.koma.crypto.entity.KeyPairEntity;
import com.wtl.koma.crypto.exception.CryptoRuntimeException;

/**
 * 算法类型
 */
public enum CipherEnum {

    DES(CryptoEnum.SYMMETRIC, "DES", KeyPairEntity.initSymmetricKey("80AC4AD5EF5D93178348C18272DXF6BD3")),
    AES(CryptoEnum.SYMMETRIC, "AES", KeyPairEntity.initSymmetricKey("76B7593457E2AB50BEFE2DXCD63CF388F")),
    DES_EDE(CryptoEnum.SYMMETRIC, "DESede", KeyPairEntity.initSymmetricKey("842C020456C108A25EDX67C644413777E3")),
    RSA(CryptoEnum.ASYMMETRIC, "RSA", KeyPairEntity.initAsymmetricKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQD36c8vjUCLy1INzUjrr//BlBPmx/oTXqvZDGFgBG90F+wRtfYfHx6rH5pB5CGw9f3M2f1NyyBSDTcx2TAz5XieT7cJ5fAoaq70KRe07+tUKgM707FDiCm33U295V67n9DNkZ1dPxv/XtlUhsgtUqO8I3aVaF8nEE92ISIJoyacQwIDAQAB",
            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAPfpzy+NQIvLUg3NSOuv/8GUE+bH+hNeq9kMYWAEb3QX7BG19h8fHqsfmkHkIbD1/czZ/U3LIFINNzHZMDPleJ5Ptwnl8ChqrvQpF7Tv61QqAzvTsUOIKbfdTb3lXruf0M2RnV0/G/9e2VSGyC1So7wjdpVoXycQT3YhIgmjJpxDAgMBAAECgYAF2pgBU/EHvAPSfpvvsMa1v/TeWK9/gJdHN0MfXMty7tOssWK/5mC3grYGQ0hGYPCewzD3gsnfFh/NBgdc2ljIPEym8BfQmveb3uxqp9LlEHCExnZroH5wG9+7gkMQ0v9/L7XtmnqWYb8OCxYHb7OtwOnaUATQdcqSoyA0/RTglQJBAP86/TUpugGi0DOoskjJrmdeTMI4V344BHhpIJBqGhLqWL/iXpXeHP2GOssXdepElEWMcH14WL0707T6eMByyOcCQQD4qSwU0TvoeW66FIITUCa9K+M+RWBZVsKlBc1jv8M0V9Oob+vRU5QT1P8TcBzxPpLiMrrREuViX5PqJ2Gw2RpFAkEA80hCNVuZvpd4B9XEYxdDE6rSVcsostoogLIMZTuNsPwKLpVS/V2jmpFJF288aVqHTqfoTeDaDT6qtPaPBTokfwJAOhL4kpwTS8YgkCD+CBiuE3xOhFqbmAHlT7k9MNC2fGEOs8X/41GLwd36/MUwXT9p+p4yd2XcYM9FsmgkLrNlqQJBAKWc+fCGxYSsPBpNJ+JSFfvDcj//puvh71E0DcORmHBIfQNsbCi+rGj6TW35S9V0LNrZQsQVbpNw5ubbiWJ7Cxs=")),
    ;

    /**
     * 10:对称加密
     * 20：非对称
     */
    private CryptoEnum type;
    private String code;
    private KeyPairEntity key;
    CipherEnum(CryptoEnum type, String code, KeyPairEntity key){
        this.type = type;
        this.code = code;
        this.key = key;
    }

    public static CipherEnum codeOf(String code){
        CipherEnum[] cipherEnums = values();
        for (CipherEnum cipherEnum : cipherEnums) {
            if (cipherEnum.getCode().equalsIgnoreCase(code)) {
                return cipherEnum;
            }
        }
        throw new CryptoRuntimeException("未找到" + code + "对应的加密枚举对象");
    }


    public CryptoEnum getType() {
        return type;
    }

    public void setType(CryptoEnum type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public KeyPairEntity getKey() {
        return key;
    }

    public void setKey(KeyPairEntity key) {
        this.key = key;
    }
}

