package com.ryan.tree;

import com.ryan.util.PrintUtil;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by MUFCRyan on 2017/10/30.
 * 练习
 */

public class D11_CustomTreeSet<AnyType extends Comparable<? super AnyType>> {
    private BinaryNode<AnyType> root;
    private int modCount;

    D11_CustomTreeSet(){
        root = null;
    }

    public void makeEmpty(){
        modCount ++;
        root = null;
    }

    private boolean isEmpty(){
        return root == null;
    }

    public boolean contains(AnyType x){
        return contains(x, root);
    }

    private boolean contains(AnyType x, BinaryNode<AnyType> tree) {
        if (tree == null )
            return false;

        int compareResult = x.compareTo(tree.element);
        if (compareResult < 0){
            return contains(x, tree.left);
        } else if (compareResult > 0){
            return contains(x, tree.right);
        } else {
            return true;
        }
    }

    /*public AnyType findMin() throws UnderflowException{
        if (isEmpty())
            throw new UnderflowException();
        else
            return findMin(root).element;
    }*/

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> tree) {
        if (tree == null)
            return null;
        else if (tree.left == null)
            return tree;
        return findMin(tree.left);
    }

    /*public AnyType findMax() throws UnderflowException{
        if (isEmpty())
            throw new UnderflowException();
        else
            return findMax(root).element;
    }*/

    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> tree) {
        if (tree == null)
            return null;
        else if (tree.right == null)
            return tree;
        return findMax(tree.right);
    }

    public void insert(AnyType x){
        root = insert(x, root, null);
    }

    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> tree, BinaryNode<AnyType> parent) {
        if (tree == null){
            modCount ++;
            return new BinaryNode<>(x, null, null, parent);
        }

        int compareResult = x.compareTo(tree.element);
        if (compareResult < 0){
            tree.left = insert(x, tree.left, tree);
        } else if (compareResult > 0){
            tree.right = insert(x, tree.right, tree);
        } else ;// duplicate
        return tree;
    }

    private void remove(AnyType x){
        root = remove(x, root);
    }

    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> tree) {
        if (tree == null)
            return null;

        int compareResult = x.compareTo(tree.element);
        if (compareResult < 0){
            tree.left = remove(x, tree.left);
        } else if (compareResult > 0){
            tree.right = remove(x, tree.right);
        } else if (tree.left != null && tree.right != null){// both two children are exist
            tree.element = findMin(tree.right).element; // 右子树中的最小值赋给当前节点
            tree.right = remove(tree.element, tree.right); // 遍历调整子树中的节点
        } else {
            modCount ++;
            BinaryNode<AnyType> oneChild;
            oneChild = (tree.left != null) ? tree.left : tree.right;
            oneChild.parent = tree.parent;
            tree = oneChild;
        }
        return tree;
    }

    public void printTree(){
        if (isEmpty())
            PrintUtil.println("Empty tree");
        else
            printTree(root);
    }

    private void printTree(BinaryNode<AnyType> tree) {
        if (tree != null){
            printTree(tree.left);
            PrintUtil.println(tree.element);
            printTree(tree.right);
        }
    }

    /*private class UnderflowException extends Exception{

    }*/

    private static class BinaryNode<AnyType>{

        private AnyType element;
        private BinaryNode<AnyType> left;
        private BinaryNode<AnyType> right;
        private BinaryNode<AnyType> parent;

        BinaryNode(AnyType element){
            this(element, null, null, null);
        }

        BinaryNode(AnyType element, BinaryNode<AnyType> leftSubTree, BinaryNode<AnyType> rightSubTree, BinaryNode<AnyType> parentTree){
            this.element = element;
            left = leftSubTree;
            right = rightSubTree;
            parent = parentTree;
        }
    }

    public Iterator iterator(){
        return new CustomTreeSetIterator();
    }

    private class CustomTreeSetIterator implements Iterator<AnyType>{
        private BinaryNode<AnyType> current = findMin(root);
        private BinaryNode<AnyType> previous;
        private int exceptedModCount = modCount;
        private boolean okToRemove = false;
        private boolean atEnd = false;

        @Override
        public boolean hasNext() {
            return !atEnd;
        }

        @Override
        public AnyType next() {
            if (modCount != exceptedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();

            AnyType nextElement = current.element;
            previous = current;

            // if there is a right child, next node is min in right subtree
            if (current.right != null){
                current = findMin(current.right);
            } else {
                // else, find ancestor that it is left of
                BinaryNode<AnyType> child = this.current;
                current = current.parent;
                while (current != null && current.left != child){
                    child = current;
                    current = current.parent;
                }
                if (current == null)
                    atEnd = true;
            }
            okToRemove = true;
            return nextElement;
        }

        @Override
        public void remove() {
            if (modCount != exceptedModCount){
                throw new ConcurrentModificationException();
            }

            if (!okToRemove){
                throw new IllegalStateException();
            }

            D11_CustomTreeSet.this.remove(previous.element);
            okToRemove = false;
        }
    }
}
