package com.ryan.list_stack_queue;

/**
 * Created by MUFCRyan on 2017/10/26.
 * 练习 3.30
 * a. 写出自调整表的数组实现，所有插入在前端进行。添加一个 find 操作，当一个元素被 find 访问时，就被移到表的前端而并不改变其余的项的相对顺序。
 * b. 写出自调整表的链表实现
 * *c. 设每个元素都有其被访问的固定的概率 Pi。证明那些具有最高访问概率的元素都靠近表的前端。证明见第十一章引用中的 Sleator 和 Tarjan's 的论文
 */

public class C30_SelfAdjustingList {

    /** 自调整表数组实现 */
    private int[] array = new int[100];
    private int sArrayIndex = array.length;

    private boolean addByArrayImplement(int x){
        if (sArrayIndex == 0){
            return false;
        }
        sArrayIndex--;
        array[sArrayIndex] = x;
        return true;
    }

    private int findByArrayImplement(int x){
        if (array.length == 0)
            return -1;
        for (int i = sArrayIndex; i < array.length; i++) {
            if (array[i] == x){
                for (int j = i; j > sArrayIndex; j--) {
                    array[j] = array[j - 1];
                }
                array[sArrayIndex] = x;
                return i;
            }
        }
        return -1;
    }


    /** 自调整表链表实现 */
    private static class Node{
        private int data;
        private Node next;
    }

    private static final Node sHead = new Node();
    private int sLinkIndex = 0;
    static {
        Node currentNode = sHead;
        for (int i = 0; i < 100; i++) {
            Node node = new Node();
            node.data = i + 1;
            currentNode.next = node;
            currentNode = currentNode.next;
        }
    }

    private void addByLinkedListImplement(int x){
        Node node = new Node();
        node.data = x;
        node.next = sHead.next;
        sHead.next = node;
    }

    /** 自调整表数组实现 */
    private int findByLinkedListImplement(int x){
        Node currentNode = C30_SelfAdjustingList.sHead;
        if (currentNode.next == null)
            return -1;
        sLinkIndex = 0;
        while (currentNode.next != null){
            sLinkIndex++;
            if (currentNode.next.data == x){
                Node target = currentNode.next;
                currentNode.next = currentNode.next.next;
                target.next = sHead.next;
                sHead.next = target;
                return sLinkIndex + 1;
            }
            currentNode = currentNode.next;
        }
        return -1;
    }
}
