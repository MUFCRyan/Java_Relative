package com.ryan.tree;

/**
 * Created by MUFCRyan on 2017/11/2.
 * 练习 4.32 设计一个递归的线性算法，该算法测试一棵二叉树是否在每一个节点都满足查找树的序的性质
 */

public class D32_CheckBST<AnyType extends Comparable<? super AnyType>> {
    private boolean checkBST(Node<AnyType> root){
        if (root == null || (root.left == null && root.right == null))
            return true;
        if (root.left != null && root.left.element.compareTo(root.element) >= 0)
            return false;
        if (root.right != null && root.right.element.compareTo(root.element) <= 0)
            return false;
        return checkBST(root.left) && checkBST(root.right);
    }
}
