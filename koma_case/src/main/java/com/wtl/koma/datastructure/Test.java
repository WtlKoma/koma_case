package com.wtl.koma.datastructure;

/**
 * @Description
 * @Author WTL
 * @Date 2021/1/15
 */
public class Test {
    //LinkedQueue
    public static void main(String[] args) throws InterruptedException {
//        LinkedBlockingQueue queue = new LinkedBlockingQueue<String>();
        LinkedQueueOneWay<String> queue = new LinkedQueueOneWay<>();
        queue.add("第1个");
        queue.add("第2个");
        queue.add("第3个");
        queue.add("第4个");

        /*System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());*/

        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println(queue.get());

    }
}
