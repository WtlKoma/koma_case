package com.wtl.koma.crypto.entity;

/**
 * 密钥对对象
 * 对于对称加密使用privateKey作为密钥
 */
public class KeyPairEntity {

    private final String publicKey;
    private final String privateKey;

    private KeyPairEntity(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /**
     * 初始化对称密钥
     * @param privateKey
     * @return
     */
    public static KeyPairEntity initSymmetricKey(String privateKey){
        return new KeyPairEntity(null, privateKey);
    }

    /**
     * 初始化非对称密钥
     * @param publicKey
     * @param privateKey
     * @return
     */
    public static KeyPairEntity initAsymmetricKey(String publicKey, String privateKey){
        return new KeyPairEntity(publicKey, privateKey);
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

}
