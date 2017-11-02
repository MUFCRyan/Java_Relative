package com.ryan.tree;

/**
 * Created by MUFCRyan on 2017/11/2.
 * 练习 4.35 写出具有生成最少节点高度 h 的 AVL 方法，该方法的运行时间是多少？
 * 思路：只要每个节点的两个儿子的高度差为 1 即是高为 h 的最少节点的 AVL 树
 */

public class D35_MinAVLTree {

    public static void main(String[] args){
        D35_MinAVLTree minAVLTree = new D35_MinAVLTree();
        int[] lastValue = {0};
        Node<Integer> tree = minAVLTree.minAVLTree(10, lastValue);
    }

    private Node<Integer> minAVLTree(int height, int[] value){
        Node<Integer> node = null;
        if (height >= 0){
            node = new Node<>(null, null, null, -1);
            node.setHeight(height);
            node.left = minAVLTree(height - 1, value);
            node.element = ++value[0];
            node.right = minAVLTree(height - 2, value);
        }
        return node;
    }
}
