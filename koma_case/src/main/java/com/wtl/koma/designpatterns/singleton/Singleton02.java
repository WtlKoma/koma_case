package com.wtl.koma.designpatterns.singleton;

/**
 * @Description 饿汉模式（线程安全）不管是否用到在类加载的时候直接创建占用资源
 * @Author WTL
 * @Date 2020/11/20
 */
public class Singleton02 {

    //类的加载的时候就会初始化此变量
    private static Singleton02 singleton = new Singleton02();

    private Singleton02 () {}

    public static Singleton02 getInstance () {
        return singleton;
    }

}
