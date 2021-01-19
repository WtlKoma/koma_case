package com.wtl.koma.datastructure;

/**
 * 单向链表
 * @Description
 * @Author WTL
 * @Date 2021/1/15
 */
public class LinkedQueueOneWay<E> {

    private Node<E> head;

    private Node<E> last;

    public LinkedQueueOneWay() {
        //last指向空节点，head指向last节点
        head = last = new Node<>(null);
    }

    void add(E e){
        //last.next指向节点，last指向last.next
        last = last.next = new Node<>(e);
    }

    E get(){
        //获取head节点
        Node<E> h = head;
        //获取第一个数据节点
        Node<E> first = h.next;
        //将h的next指向自己本身 //有助 GC
        h.next = h;
        //将head指向第一个节点
        head = first;
        //获取第一个节点的值
        E item = first.item;
        //清空第一个节点值（head的item永远为null，last的next永远为null）
        first.item = null;
        return item;
    }

    private class Node<E>{

        E item;

        Node<E> next;

        Node (E e) {
            item = e;
        }

    }
}
