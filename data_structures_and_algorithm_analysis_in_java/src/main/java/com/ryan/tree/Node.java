package com.ryan.tree;

public class Node<AnyType extends Comparable<? super AnyType>> {
    public Node<AnyType> previous;
    public Node<AnyType> left;
    public Node<AnyType> right;
    public AnyType element;
    public int height = 0;

    Node(Node<AnyType> previous, Node<AnyType> left, Node<AnyType> right, AnyType element) {
        this.previous = previous;
        this.left = left;
        this.right = right;
        this.element = element;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}