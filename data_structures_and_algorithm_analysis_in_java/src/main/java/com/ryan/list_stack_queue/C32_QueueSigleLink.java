package com.ryan.list_stack_queue;

/**
 * Created by MUFCRyan on 2017/10/26.
 * 练习 3.32 使用单链表高效实现队列类，策略：头部插入，尾部删除
 */

public class C32_QueueSigleLink {
    private class Node{
        private int data;
        private Node next;
    }

    private Node head;

    /** 前部插入 */
    private void enqueue(int x){
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

    /** 尾部删除 */
    private Node dequeue(){
        Node currentNode = head;
        if (currentNode == null)
            return null;
        if (currentNode.next == null){
            head = null;
            return currentNode;
        }
        Node target = currentNode;
        while (currentNode.next != null){
            target = currentNode;
            currentNode = currentNode.next;
        }
        Node node = target.next;
        target.next = null;
        return node;
    }
}
