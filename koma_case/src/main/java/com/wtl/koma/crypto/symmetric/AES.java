package com.wtl.koma.crypto.symmetric;


import com.wtl.koma.crypto.AbstractCrypto;
import com.wtl.koma.crypto.enums.CipherEnum;

/**
 * aes加密  单例对象
 */
public class AES extends AbstractSymmetric {

    /**
     * 加密实例对象，添加volatile防止指令重排造成线程不安全
     */
    private volatile static AbstractCrypto instance = null;

    private AES(InitParam initParam){
        super(initParam);
    }

    public static AbstractCrypto getInstance(CipherEnum cipherEnum) {
        if (instance == null) {
            synchronized (AES.class) {
                if (instance == null) {
                    instance = new AES(getInitParam(cipherEnum));
                }
            }
        }
        return instance;
    }

}
