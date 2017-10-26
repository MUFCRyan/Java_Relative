package com.ryan.list_stack_queue;

import com.ryan.util.PrintUtil;

/**
 * Created by MUFCRyan on 2017/10/26.
 * 练习 3.34 给定一个 含有 N 个节点的链表（无头尾节点），但是 N 大小未知
 * a. 设计一个 O(N) 算法以确定该表是否包含有循环。可以使用 O(N) 的额外控件
 * #b. 重复(a)部分，但是只使用 O(1) 的额外空间。（提示：使用两个迭代器，它们最初在表的开始处，但以不同的速度推进）
 */

public class C34_CertifyCircleLink {
    public static void main(String[] args){
        PrintUtil.println(checkIsCircleLinkA(sHead));
        PrintUtil.println(checkIsCircleLinkB(sHead));
    }

    private static class Node{
        private int data;
        private Node next;
        private int count = 0;
    }

    private static Node sHead = new Node();

    static {
        sHead.data = 1;
        Node head = sHead;
        for (int i = 1; i < 10; i++) {
            Node node = new Node();
            node.data = i + 1;
            head.next = node;
            head = head.next;
        }

        head = sHead;
        while (head.next != null){
            head = head.next;
        }

        head.next = sHead.next.next;
    }

    /**
     * a. 解决思路，Node 保存被访问次数，只要是循环队列，那么一定有一个节点会被访问两次及以上（此时其他节点被访问了一次），否则访问到最后的节点的下一个节点就会为
     * null，就不是循环队列
     * */
    private static int checkIsCircleLinkA(Node head){
        // 总要对链表进行遍历，所以时间复杂度为 O(N)
        while (head != null){
            head.count++;
            if (head.count > 1)
                return head.data;
            head = head.next;
        }
        return -1;
    }

    /**
     * b. 解决思路，两个迭代器同时遍历链表，但是速度不同，只要是循环队列，那么二者一定会相遇 —— 即二者当前遍历到的节点相同，否则访问到最后的节点的下一个节点就会为
     * null，就不是循环队列
     * */
    private static boolean checkIsCircleLinkB(Node head){
        Node firstIterator = head;
        Node secondIterator = head.next;
        // 总要对链表进行遍历，所以时间复杂度为 O(N)
        while (firstIterator != null && secondIterator.next != null){
            if (firstIterator.data == secondIterator.data)
                return true;
            firstIterator = firstIterator.next;
            secondIterator = secondIterator.next.next;
        }
        return false;
    }
}
