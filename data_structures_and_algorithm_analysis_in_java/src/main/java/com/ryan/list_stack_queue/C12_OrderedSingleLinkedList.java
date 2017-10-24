package com.ryan.list_stack_queue;

import com.ryan.util.PrintUtil;

/**
 * Created by MUFCRyan on 2017/10/24.
 * 练习 3.12 保持单链表以排序的顺序重复练习3.11
 */

public class C12_OrderedSingleLinkedList {
    public static void main(String[] args){

    }

    private static class Node<Comparable>{
        private Comparable data;
        private Node<Comparable> next;
    }

    private static int count = 0;
    private static Node<Comparable> head = new Node<>();

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
    private static boolean contains(Comparable x){
        Node<Comparable> currentNode = head.next;
        while (currentNode != null && currentNode.data.compareTo(x) <= 0){
            if (x.equals(currentNode.data))
                return true;
            else
                currentNode = currentNode.next;
        }
        return false;
    }

    /** d */
    private static boolean addIfNotExist(Comparable x){
        if (contains(x))
            return false;

        Node node = new Node();
        node.data = x;

        Node<Comparable> currentNode = head;
        while (currentNode != null && currentNode.data.compareTo(x) < 0){
            if (currentNode.next == null){
                currentNode.next = node;
                return true;
            } else if (currentNode.next.data.compareTo(x) > 0){
                node.next = currentNode.next;
                currentNode.next = node;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    /** e */
    private static boolean delete(Comparable x){
        if (!contains(x))
            return false;


        Node<Comparable> currentNode = head;
        while (currentNode != null && currentNode.data.compareTo(x) < 0){
            if (currentNode.next.data.equals(x)){
                currentNode.next = currentNode.next.next;
                return true;
            } else {
                currentNode = currentNode.next;
            }
        }
        return false;
    }
}
