package com.ryan.tree;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by zhaofengchun on 2017/10/30.
 * 练习 4.13 编写 TreeSet 类的实现程序，其中相关的迭代器使用二叉查找树。在每个节点上添加通向下一个最小节点和下一个最大节点的链。
 *      为使所编程序更简单，添加头节点和尾节点，它们不属于二叉树的一部分，但有助于使程序的链表部分更简单。
 */

public class D13_CustomTreeSet2<AnyType extends Comparable<? super AnyType>> {
    private BinaryNode<AnyType> root;
    private int modCount = 0;

    D13_CustomTreeSet2(){
        root = null;
    }

    public void makeEmpty(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public boolean contains(AnyType element){
        if (element == null)
            return false;

        return contains(element, root);
    }

    private boolean contains(AnyType element, BinaryNode<AnyType> tree) {
        if (tree == null)
            return false;
        int compareResult = element.compareTo(tree.element);
        if (compareResult < 0){
            return contains(element, tree.left);
        } else if (compareResult > 0){
            return contains(element, tree.right);
        } else
            return true;
    }

    private AnyType findMax() throws UnderflowException{
        if (isEmpty())
            throw new UnderflowException();
        else
            return findMax(root).element;
    }

    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> tree) {
        if (tree == null)
            return null;
        if (tree.right == null)
            return tree;
        else
            return findMax(tree.right);
    }

    private AnyType findMin() throws UnderflowException{
        if (isEmpty())
            throw new UnderflowException();
        else
            return findMin(root).element;
    }

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> tree){
        if (tree == null)
            return null;
        if (tree.left == null)
            return tree;
        else
            return findMin(tree.left);
    }

    public void insert(AnyType element){
        root = insert(element, root, null, null);
    }

    private BinaryNode<AnyType> insert(AnyType element, BinaryNode<AnyType> tree, BinaryNode<AnyType> next, BinaryNode<AnyType> previous) {
        if (tree == null){
            modCount ++;
            BinaryNode<AnyType> newNode = new BinaryNode<>(element, null, null, next, previous);
            if (next != null)
                next.previous = newNode;

            if (previous != null)
                previous.next = newNode;

            return newNode;
        }

        int compareResult = element.compareTo(tree.element);
        if (compareResult < 0){
            tree.left = insert(element, tree.left, tree, previous);
        } else if (compareResult > 0){
            tree.right = insert(element, tree.right, next, tree);
        } else ; // Duplicate
        return tree;
    }

    private void remove(AnyType element){
        root = remove(element, root);
    }

    private BinaryNode<AnyType> remove(AnyType element, BinaryNode<AnyType> tree) {
        if (tree == null)
            return tree;
        int compareResult = element.compareTo(tree.element);
        if (compareResult < 0){
            tree.left = remove(element, tree.left);
        } else if (compareResult > 0){
            tree.right = remove(element, tree.right);
        } else if (tree.left != null && tree.right != null){
            tree.element = findMin(tree.right).element;
            tree.right = remove(tree.element, tree.right); // 遍历删除被替换的右子树的最小节点
        } else {
            modCount ++;
            tree.previous.next = tree.next;
            tree.next.previous = tree.previous;
            tree = (tree.left != null) ? tree.left : tree.right;
        }
        return tree; // Match
    }

    private static class BinaryNode<AnyType>{

        private AnyType element;
        private BinaryNode<AnyType> left;
        private BinaryNode<AnyType> right;
        private BinaryNode<AnyType> next;
        private BinaryNode<AnyType> previous;

        BinaryNode(AnyType element){
            this(element, null, null, null, null);
        }

        BinaryNode(AnyType element, BinaryNode<AnyType> leftSubTree, BinaryNode<AnyType>
                rightSubTree, BinaryNode<AnyType> next, BinaryNode<AnyType> previous){
            this.element = element;
            left = leftSubTree;
            right = rightSubTree;
            this.next = next;
            this.previous = previous;
        }
    }

    public Iterator iterator(){
        return new CustomTreeSetIterator();
    }

    public class CustomTreeSetIterator implements Iterator<AnyType> {

        private BinaryNode<AnyType> current = findMin(root);
        private BinaryNode<AnyType> previous;
        private int expectedCount = modCount;
        private boolean okToRemove = false;
        private boolean atEnd = false;

        @Override
        public boolean hasNext() {
            return !atEnd;
        }

        @Override
        public AnyType next() {
            if (expectedCount != modCount)
                throw new ConcurrentModificationException();

            if (!hasNext())
                throw new NoSuchElementException();

            AnyType nextElement = current.element;
            previous = current;
            current = current.next;
            okToRemove = true;
            return nextElement;
        }

        @Override
        public void remove() {
            if (expectedCount != modCount)
                throw new ConcurrentModificationException();

            if (!hasNext())
                throw new IllegalStateException();

            D13_CustomTreeSet2.this.remove(previous.element);
            okToRemove = false;
        }
    }
}

class UnderflowException extends Exception {

}
