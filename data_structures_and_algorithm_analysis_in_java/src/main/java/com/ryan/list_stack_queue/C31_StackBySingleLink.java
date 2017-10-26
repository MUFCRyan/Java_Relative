package com.ryan.list_stack_queue;

/**
 * Created by MUFCRyan on 2017/10/26.
 * 练习 3.31 使用单链表高效实现栈类，不用头结点和尾结点
 */

public class C31_StackBySingleLink {
    private Node head;
    private class Node{
        private int data;
        private Node next;
    }

    private void push(int x){
        if (head == null){
            head = new Node();
            head.data = x;
        } else {
            Node node = new Node();
            node.data = x;
            Node movedNode = this.head;
            head = node;
            head.next = movedNode;
        }
    }

    private Node pop(){
        if (head == null)
            return null;
        Node currentNode = this.head;
        head = head.next;
        return currentNode;
    }
}
