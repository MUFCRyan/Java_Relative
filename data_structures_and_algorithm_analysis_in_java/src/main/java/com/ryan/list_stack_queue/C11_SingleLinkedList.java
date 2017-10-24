package com.ryan.list_stack_queue;

import com.ryan.util.PrintUtil;

/**
 * Created by MUFCRyan on 2017/10/24.
 * 练习 3.11 假设单链表使用一个头结点实现，但无尾结点，并假设它只保留对该头结点的引用。编写一个类，包含
 * a. 返回链表大小的方法
 * b. 打印链表的方法
 * c. 测试值 x 是否含于链表的方法
 * d. 如果值 x 尚未含于链表，添加值 x 到该链表的方法
 * e. 如果值 x 含于链表，将 x 从该链表删除的方法
 */

public class C11_SingleLinkedList {
    public static void main(String[] args){

    }

    private static class Node{
        private int data;
        private Node next;
    }

    private static int count = 0;
    private static Node head = new Node();

    /** a */
    private static int size(){
        Node currentNode = head;
        while (currentNode.next != null){
            count ++;
            currentNode = currentNode.next;
        }
        return count;
    }

    /** b */
    private static void print(){
        Node currentNode = head.next;
        if (currentNode == null)
            PrintUtil.print(null);
        while (currentNode != null){
            PrintUtil.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
    }

    /** c */
    private static boolean contains(int x){
        Node currentNode = head.next;
        while (currentNode != null){
            if (currentNode.data == x)
                return true;
            currentNode = currentNode.next;
        }
        return false;
    }

    /** d */
    private static boolean addIfNotExist(int x){
        Node currentNode = head;
        while (currentNode.next != null){
            if (currentNode.data == x)
                return false;
            currentNode = currentNode.next;
        }

        if (currentNode.data == x)
            return false;

        Node node = new Node();
        node.data = x;
        currentNode.next = node;
        return true;
    }

    /** e */
    private static boolean delete(int x){
        Node currentNode = head;
        while (currentNode.next != null){
            if (currentNode.next.data == x){
                currentNode.next = currentNode.next.next;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }
}
