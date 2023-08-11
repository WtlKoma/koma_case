package com.wtl.koma.crypto.symmetric;


import com.wtl.koma.crypto.AbstractCrypto;
import com.wtl.koma.crypto.exception.CryptoRuntimeException;

import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AbstractSymmetric extends AbstractCrypto {


    protected AbstractSymmetric(InitParam initParam) {
        super(initParam);
    }

    /**
     * 文件file进行加密并保存目标文件destFile中
     *
     * @param sourceFilePath   要加密的文件 如c:/test/srcFile.txt
     * @param destFilePath 加密后存放的文件名 如c:/加密后文件.txt
     */
    @Override
    public void encrypt(String sourceFilePath, String destFilePath) {
        try (
                InputStream is = Files.newInputStream(Paths.get(sourceFilePath));
                OutputStream out = Files.newOutputStream(Paths.get(destFilePath));
                CipherInputStream cis = new CipherInputStream(is, this.enCipher);
        ) {
            byte[] buffer = new byte[READER_1M];
            int len;
            while ((len = cis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new CryptoRuntimeException("加密失败：", e);
        }
    }

    /**
     * 文件采用DES算法解密文件
     *
     * @param sourceFilePath     已加密的文件 如c:/加密后文件.txt
     *                     * @param destFile
     *                     解密后存放的文件名 如c:/ test/解密后文件.txt
     * @param destFilePath
     */
    @Override
    public void decrypt(String sourceFilePath, String destFilePath) {
        try (
                InputStream in = Files.newInputStream(Paths.get(sourceFilePath));
                OutputStream out = Files.newOutputStream(Paths.get(destFilePath));
                CipherOutputStream cos = new CipherOutputStream(out, this.deCipher);
        ) {
            byte[] buffer = new byte[READER_1M];
            int len;
            while ((len = in.read(buffer)) >= 0) {
                cos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            throw new CryptoRuntimeException("解密失败：", e);
        }
    }

}
