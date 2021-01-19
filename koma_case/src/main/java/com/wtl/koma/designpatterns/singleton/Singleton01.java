package com.wtl.koma.designpatterns.singleton;

/**
 * @Description 懒汉模式
 * @Author WTL
 * @Date 2020/11/20
 */
public class Singleton01 {

    private static Singleton01 singleton;

    private Singleton01(){}

//    public static Singleton01 getInstance () {//(线程不安全)
    public synchronized static Singleton01 getInstance () { //线程安全，但是影响并发
        if (singleton == null) singleton = new Singleton01();
        return singleton;

    }

}
