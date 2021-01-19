package com.wtl.koma.designpatterns.singleton;

/**
 * @Description 双重锁（线程安全）懒加载
 * @Author WTL
 * @Date 2020/11/20
 */
public class Singleton04 {

    private static Singleton04 instance;

    private Singleton04() {}

    public static Singleton04 getInstance () {
        if(null != instance) return instance;
        synchronized (Singleton04.class) {
            if (null != instance) return instance;
            instance = new Singleton04();
        }

        return instance;
    }

}
