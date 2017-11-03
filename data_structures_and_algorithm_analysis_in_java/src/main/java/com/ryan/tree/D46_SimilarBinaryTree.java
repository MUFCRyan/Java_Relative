package com.ryan.tree;

/**
 * Created by MUFCRyan on 2017/11/3.
 * 练习 4.46 编写一个方法以确定两棵树是否是相似的，你的方法的运行时间如何
 * 练习 4.47 编写一个方法以确定两棵树是否是同构的，你的方法的运行时间如何
 */

public class D46_SimilarBinaryTree<AnyType extends Comparable<? super AnyType>> {
    /** 两棵二叉树同构 */
    private boolean similarWithElement(Node<AnyType> tree1, Node<AnyType> tree2){
        if (tree1 == null || tree2 == null)
            return (tree1 == null && tree2 == null);
        if (tree1.element.compareTo(tree2.element) == 0){
            return (similarWithElement(tree1.left, tree2.left) && similarWithElement(tree1.right, tree2.right)) || (similarWithElement(tree1.left, tree2.right) && similarWithElement(tree1.right, tree2.left));
        } else {
            return false;
        }
    }

    /** 两棵二叉树相似 */
    private boolean similar(Node<AnyType> tree1, Node<AnyType> tree2){
        if (tree1 == null || tree2 == null)
            return (tree1 == null && tree2 == null);
        return similar(tree1.left, tree2.left) && similar(tree1.right, tree2.right);
    }
}
