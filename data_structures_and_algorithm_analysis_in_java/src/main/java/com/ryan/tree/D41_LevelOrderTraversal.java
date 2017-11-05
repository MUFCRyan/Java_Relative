package com.ryan.tree;

import com.ryan.util.PrintUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zhaofengchun on 2017/11/2.
 * 练习 4.41 编写一个例程以层序列出二叉树的节点，线性时间内完成
 *      思路：使用队列进行
 */

public class D41_LevelOrderTraversal<AnyType extends Comparable<? super AnyType>> {

    public static void main(String[] args){
        D23_InsertAVLNoRecursion<Integer> insertAVLNoRecursion = new D23_InsertAVLNoRecursion<>();
        Node<Integer> root = new Node<>(null, null, null, 1);
        for (int i = 2; i <= 15; i++) {
            Integer element = i;
            root = insertAVLNoRecursion.insert(element, root);
        }

        D41_LevelOrderTraversal<Integer> levelOrderTraversal = new D41_LevelOrderTraversal<>();
        levelOrderTraversal.traversal(new LinkedList<Node<Integer>>(), root);
    }

    private void traversal(Queue<Node<AnyType>> queue, Node<AnyType> root){
        if (queue == null)
            queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node<AnyType> node = queue.poll();
            PrintUtil.println(node.element);
            if (node.left != null){
                queue.add(node.left);
            }
            if (node.right != null){
                queue.add(node.right);
            }
        }
    }
}