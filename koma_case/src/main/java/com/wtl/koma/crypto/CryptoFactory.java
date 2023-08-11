package com.wtl.koma.crypto;


import com.wtl.koma.crypto.asymmetirc.RSA;
import com.wtl.koma.crypto.enums.CipherEnum;
import com.wtl.koma.crypto.exception.CryptoRuntimeException;
import com.wtl.koma.crypto.symmetric.AES;
import com.wtl.koma.crypto.symmetric.DES;
import com.wtl.koma.crypto.symmetric.DESede;

/**
 * 加密创建工厂
 */
public class CryptoFactory {

    /**
     * 返回单例对象
     *
     * @param cipher 算法类型
     * @return
     */
    public static AbstractCrypto getInstance(String cipher) {
        CipherEnum cipherEnum = CipherEnum.codeOf(cipher);
        return getInstance(cipherEnum);
    }

    public static AbstractCrypto getInstance(CipherEnum cipherEnum) {
        switch (cipherEnum) {
            case DES:
                return DES.getInstance(cipherEnum);
            case AES:
                return AES.getInstance(cipherEnum);
            case DES_EDE:
                return DESede.getInstance(cipherEnum);
            case RSA:
                return RSA.getInstance(cipherEnum);
            default:
                throw new CryptoRuntimeException("未找到加密枚举类对应类型【" + cipherEnum.getType() + "】实现");
        }

    }

}
