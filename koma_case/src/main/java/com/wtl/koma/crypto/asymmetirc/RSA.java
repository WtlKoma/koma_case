package com.wtl.koma.crypto.asymmetirc;

import com.wtl.koma.crypto.AbstractCrypto;
import com.wtl.koma.crypto.enums.CipherEnum;
import com.wtl.koma.crypto.exception.CryptoRuntimeException;
import lombok.Data;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class RSA extends AbstractAsymmetric {

    private static Size thisSize;

    /**
     * 加密实例对象，添加volatile防止指令重排造成线程不安全
     */
    private volatile static AbstractCrypto instance = null;

    public static AbstractCrypto getInstance(CipherEnum cipherEnum) {
        if (instance == null) {
            synchronized (RSA.class) {
                if (instance == null) {
                    /**
                     * 如果长度超过此长度则会报错 Data must not be longer than ... bytes
                     * 设置密钥大小及加解密明文长度
                     * 加密最大明文大小：1024(byte) / 8 - 11(byte)
                     * 解密最大明文大小：1024(byte) / 8
                     */
                    thisSize = Size.create(1024);
                    instance = new RSA(getInitParam(cipherEnum));
                }
            }
        }
        return instance;
    }

    private RSA(InitParam initParam) {
        super(initParam);
    }

    @Override
    public void encrypt(String sourceFilePath, String destFilePath) {
        handleData(new File(sourceFilePath), new File(destFilePath), thisSize.encryptSize, this.enCipher);
    }

    @Override
    public void decrypt(String sourceFilePath, String destFilePath) {
        handleData(new File(sourceFilePath), new File(destFilePath), thisSize.decryptSize, this.deCipher);
    }

    /**
     * 处理加解密数据
     * @param file 源文件
     * @param destFile  目标文件
     * @param singleMax 单次处理最大字节数组大小
     * @param cipher 加解密算法
     */
    private void handleData(File file, File destFile, int singleMax, Cipher cipher) {
        // 获取数据最大长度，并作其余初始化
        try (
                FileInputStream in = new FileInputStream(file);
                FileOutputStream os = new FileOutputStream(destFile)
        ) {
            int len;
            // 每次读取源文件的大小
            byte[] bytes = new byte[READER_1M];
            // 里循环每次读取的开始下标
            int offset;
            // 每此外循环读取中的分段读取次数
            int i;
            while ((len = in.read(bytes)) > 0) {
                i = 0;
                offset = 0;
                while (len - offset > 0) {
                    if (len - offset > singleMax) {
                        // 如果本次读取数组中的长度超过了最大的单次限制则使用最大的限制大小
                        os.write(cipher.doFinal(bytes, offset, singleMax));
                    } else {
                        os.write(cipher.doFinal(bytes, offset, len - offset));
                    }
                    i++;
                    // 计算下次读取的下标
                    offset = i * singleMax;
                }
            }
        } catch (Exception e) {
            throw new CryptoRuntimeException("RSA处理文件失败：", e);
        }
    }

    /*public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator kpg;
            // 添加提供者 BC
            // <!-- 加密工具 -->
            // <dependency>
            //    <groupId>org.bouncycastle</groupId>
            //    <artifactId>bcprov-jdk15on</artifactId>
            //    <version>1.69</version>
            // </dependency>
            Security.addProvider(new BouncyCastleProvider());
            // 密钥对实例化，指定算法及提供者
            kpg = KeyPairGenerator.getInstance(CipherEnum.RSA.getCode(), "BC");
            assert kpg != null : "key generate error.";
            // 密钥对初始化，指定密钥大小
            kpg.initialize(thisSize.getKeySize());
            // 获取生成的密钥对
            return kpg.generateKeyPair();
            *//*loadPublicKey(kpg.generateKeyPair().getPublic());
            loadPrivateKey(kpg.generateKeyPair().getPrivate());*//*
        } catch (Exception e) {
            return null;
        }
    }*/

    /**
     * 加载公钥字符串
     *
     * @param publicKey 公钥对象
     * @return 公钥字符串
     */
    private static String loadPublicKey(PublicKey publicKey) {
        return keyToString(publicKey);
    }

    /**
     * 加载私钥字符串
     *
     * @param privateKey 私钥对象
     * @return 私钥字符串
     */
    private static String loadPrivateKey(PrivateKey privateKey) {
        return keyToString(privateKey);
    }

    /**
     * 将 Key 对象转换成 字符串
     *
     * @param key Key
     * @return 字符串
     */
    private static String keyToString(Key key) {
        try {
            byte[] keyBytes = key.getEncoded();
            return new String(java.util.Base64.getEncoder().encode(keyBytes), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Data
    public static class Size {
        /**
         * 密钥大小
         */
        private Integer keySize;
        /**
         * 最大加密大小
         */
        private Integer encryptSize;
        /**
         * 最大解密大小
         */
        private Integer decryptSize;

        // --------------------------------------- 直接创建并配置

        /**
         * 创建配置 密钥大小等级
         *
         * @param keySize 密钥大小
         * @return 密钥配置
         */
        public static Size create(Integer keySize) {
            // 大于等于 512
            assert keySize >= 512;
            // 小于等于 65535
            assert keySize <= 65535;
            // 必须是 64 的整数倍
            assert keySize % 64 == 0;
            return new Size(keySize);
        }

        private Size(Integer keySize) {
            this.keySize = keySize;
            this.encryptSize = keySize / 8 - 11;
            this.decryptSize = keySize / 8;
        }
    }
}
