package com.ryan.tree;

/**
 * Created by MUFCRyan on 2017/11/2.
 * 练习 4.26 写出执行双旋转的方法，其效率要超过执行两个单旋转的方法
 */

public class D26_DoubleRotations<AnyType extends Comparable<? super AnyType>> {
    private static class Node<AnyType>{
        private Node<AnyType> left;
        private Node<AnyType> right;
        private AnyType element;
        private int height = 0;
    }

    private Node<AnyType> lrRotation(Node<AnyType> k3){
        Node<AnyType> k1 = k3.left;
        Node<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k3.left = k2.right;
        k2.left = k1;
        k2.right = k3;
        k1.height = Math.max(getHeight(k1.left), getHeight(k1.right)) + 1;
        k3.height = Math.max(getHeight(k3.left), getHeight(k3.right)) + 1;
        k2.height = Math.max(getHeight(k2.left), getHeight(k2.right)) + 1;
        return k2;
    }

    private Node<AnyType> rlRotation(Node<AnyType> k3){
        Node<AnyType> k1 = k3.right;
        Node<AnyType> k2 = k1.left;
        k1.left = k2.right;
        k3.right = k2.left;
        k2.right = k1;
        k2.left = k3;
        k1.height = Math.max(getHeight(k1.left), getHeight(k1.right)) + 1;
        k3.height = Math.max(getHeight(k3.left), getHeight(k3.right)) + 1;
        k2.height = Math.max(getHeight(k2.left), getHeight(k2.right)) + 1;
        return k2;
    }

    private int getHeight(Node<AnyType> node){
        if (node == null)
            return 0;
        return node.height;
    }
}
