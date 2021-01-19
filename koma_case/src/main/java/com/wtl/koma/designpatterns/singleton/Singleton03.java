package com.wtl.koma.designpatterns.singleton;

/**
 * @Description 静态内部类(线程安全，懒加载)，JVM虚拟机可以确保多线程并发访问的正确性，
 *              也就是一个类的构造方法在多线程环境下可以正确被加载
 * @Author WTL
 * @Date 2020/11/20
 */
public class Singleton03 {

    //静态内部类只有在被调用的时候才会被加载
    private static class Singleton {
        private static Singleton03 instance = new Singleton03();
    }

    private Singleton03() {}

    public static Singleton03 getInstance () {
        return Singleton.instance;
    }

}
