package com.wtl.koma.datastructure;

/**
 * 双向链
 * @Description
 * @Author WTL
 * @Date 2021/1/18
 */
public class LinkedQueueTwoWay {



    private class Node<E> {

        E item;

        Node<E> next;

        Node<E> prev;

        public Node(E e){
            item = e;
        }

    }

}
