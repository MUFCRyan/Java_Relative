package com.ryan.list_stack_queue;

import java.util.List;

/**
 * Created by MUFCRyan on 2017/10/24.
 * 练习 3.20 懒惰删除，标记已删除和未删除的元素，当二者数目一样多时，才遍历整个表并执行真正的删除
 * a. 列出懒惰删除的优缺点
 * b. 编写使用懒惰删除实现标准链表操作的相应例程
 */

public class C20_LazyDeletion {
    /**
     * a. 优点：统一删除减少了多次删除的重复操作，由此对于 ArrayList 来说将每次删除后都要移动后续元素的情况大大改善，
     *    只需要在最后一次整体遍历时删除并移动即可；另外对于误删操作也能快速恢复；
     *
     *    缺点：进行标记而不是立即删除意味着不能及时空出足够的空间，对于空间有限的情况下可能影响未来的添加操作；同时对于查
     *    询操作因为总数未能及时减少，也不能提高查询效率和速度
     *
     * b. 只编写 remove/contains方法
     * */


    private class Node {
        private Node next;
        private int data;
        private boolean isDelete = false;
    }

    private List<Node> items;
    private Node head = new Node();
    private int removeNum = 0;
    private int size = 0;

    private boolean remove(int x){
        Node node = head.next;
        while (node != null && !node.isDelete){
            if (node.data == x){
                removeNum++;
                size--;
                node.isDelete = true;
                if (removeNum == size){
                    // 遍历整个表删除被标记的节点
                    for (int i = items.size() - 1; i >= 0; i--) {
                        if (items.get(i).isDelete)
                            items.remove(i);
                    }
                    removeNum = 0;
                }
                return true;
            }
            node = node.next;
        }
        return false;
    }

    private boolean contains(int x){
        Node node = head.next;
        while (node != null && !node.isDelete){
            if (node.data == x){
                return true;
            }
            node = node.next;
        }
        return false;
    }
}
