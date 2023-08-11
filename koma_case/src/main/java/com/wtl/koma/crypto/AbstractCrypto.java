package com.wtl.koma.crypto;

import com.wtl.koma.crypto.enums.CipherEnum;
import com.wtl.koma.crypto.exception.CryptoRuntimeException;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public abstract class AbstractCrypto {

    /**
     * 读流的大小
     */
    protected static int READER_1M = 2 << 9;

    /**
     * 密码格式，初始化加密算法
     */
    protected final Cipher enCipher;
    protected final Cipher deCipher;

    protected AbstractCrypto(InitParam initParam) {
        this.enCipher = initParam.getEnCipher();
        this.deCipher = initParam.getDeCipher();
    }

    public abstract void encrypt(String sourceFilePath, String destFilePath);

    public abstract void decrypt(String sourceFilePath, String destFilePath);

    protected static InitParam getInitParam(CipherEnum cipherEnum) {
        try {
            Cipher enCipher;
            Cipher deCipher;
            // 初始化密钥
            switch (cipherEnum) {
                case RSA:
                    KeyFactory keyFactory = KeyFactory.getInstance(cipherEnum.getCode());
                    // 生成公钥
                    RSAPublicKey rsaPublicKey = (RSAPublicKey) loadPublicKey(cipherEnum);
                    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
                    Key publicKey = keyFactory.generatePublic(x509KeySpec);
                    // 指定配置并初始化
                    enCipher = Cipher.getInstance(keyFactory.getAlgorithm());
                    enCipher.init(Cipher.ENCRYPT_MODE, publicKey);

                    // 生成私钥
                    PrivateKey rsaPrivateKey = loadPrivateKey(cipherEnum);
                    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
                    Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

                    // 指定配置并初始化
                    deCipher = Cipher.getInstance(keyFactory.getAlgorithm());
                    deCipher.init(Cipher.DECRYPT_MODE, privateKey);
                    break;
                default:
                    KeyGenerator generator = KeyGenerator.getInstance(cipherEnum.getCode());
                    generator.init(new SecureRandom(cipherEnum.getKey().getPrivateKey().getBytes()));
                    SecretKey key = generator.generateKey();
                    enCipher = Cipher.getInstance(cipherEnum.getCode());
                    enCipher.init(Cipher.ENCRYPT_MODE, key);
                    deCipher = Cipher.getInstance(cipherEnum.getCode());
                    deCipher.init(Cipher.DECRYPT_MODE, key);
            }

            return new InitParam(enCipher, deCipher);
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoRuntimeException("初始化密钥失败:", e);
        } catch (NoSuchPaddingException | InvalidKeyException e) {
            throw new CryptoRuntimeException("初始化加密算法失败:", e);
        } catch (InvalidKeySpecException e) {
            throw new CryptoRuntimeException("生成公钥失败:", e);
        }
    }

    /**
     * 加载公钥（非对称）
     * @param cipherEnum 加密类型枚举
     * @return 公钥对象
     */
    private static PublicKey loadPublicKey(CipherEnum cipherEnum) {
        try {
            /**
             * 通过 公钥字节数组，指定 算法，再次生成 公钥对象
             * 公钥 使用 X509EncodedKeySpec
             */
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode((cipherEnum.getKey().getPublicKey().getBytes())));
            KeyFactory keyFactory = KeyFactory.getInstance(cipherEnum.getCode());
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new CryptoRuntimeException("加载公钥失败：", e);
        }
    }

    /**
     * 加载私钥（非对称）
     *
     * @param cipherEnum 加密类型枚举
     * @return 私钥对象
     */
    private static PrivateKey loadPrivateKey(CipherEnum cipherEnum) {
        try {
            // 通过 私钥字节数组，指定 算法，再次生成 私钥对象
            // 私钥 使用 PKCS8EncodedKeySpec
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode((cipherEnum.getKey().getPrivateKey().getBytes())));
            KeyFactory keyFactory = KeyFactory.getInstance(cipherEnum.getCode());
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new CryptoRuntimeException("加载私钥失败：", e);
        }
    }

    @Data
    @AllArgsConstructor
    public static class InitParam {
        /**
         * 加密算法对象
         */
        private Cipher enCipher;
        /**
         * 解密算法对象
         */
        private Cipher deCipher;

    }
}
