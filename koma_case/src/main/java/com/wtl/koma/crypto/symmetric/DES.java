package com.wtl.koma.crypto.symmetric;


import com.wtl.koma.crypto.AbstractCrypto;
import com.wtl.koma.crypto.enums.CipherEnum;

/**
 * des加密  单例对象
 */
public class DES extends AbstractSymmetric {

    /**
     * 加密实例对象，添加volatile防止指令重排造成线程不安全
     */
    private volatile static AbstractCrypto instance = null;

    private DES(InitParam initParam){
        super(initParam);
    }

    public static AbstractCrypto getInstance(CipherEnum cipherEnum) {
        if (instance == null) {
            synchronized (DES.class) {
                if (instance == null) {
                    instance = new DES(getInitParam(cipherEnum));
                }
            }
        }
        return instance;
    }

}
