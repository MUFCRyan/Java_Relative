package com.ryan.list_stack_queue;

/**
 * Created by MUFCRyan on 2017/10/23.
 * 练习 3.3 实现 MyLinkedList 的 contains 例程
 */

public class C03_Contains {
    public static void main(String[] args){

    }

    private static Node beginMaker;
    private static Node endMaker;

    private static boolean contains(AnyType type){

        if (beginMaker.next == null)
            return false;

        Node node = beginMaker.next;

        while ((node != endMaker) && (node.data != type)) {
            node = node.next;
        }
        return node != endMaker;
    }

    private static class AnyType{}

    private static class Node{
        AnyType data;
        Node pre;
        Node next;
    }
}
