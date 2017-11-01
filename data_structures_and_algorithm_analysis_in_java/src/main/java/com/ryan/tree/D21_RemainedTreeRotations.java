package com.ryan.tree;

/**
 * Created by zhaofengchun on 2017/10/31.
 * 练习 4.21 写出实现 AVL 单旋转和双旋转的其余的过程
 */

public class D21_RemainedTreeRotations<AnyType>{
    private static class AvlNode<AnyType> {

        AnyType mElement;
        AvlNode mLeft;
        AvlNode mRight;
        int height;

        AvlNode(AnyType element){
            this(element, null, null);
        }

        AvlNode(AnyType element, AvlNode left, AvlNode right){
            mElement = element;
            mLeft = left;
            mRight = right;
        }
    }

    private int height(AvlNode<AnyType> tree){
        return tree == null ? -1 : tree.height;
    }

    /** LL Rotation */
    private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2){
        AvlNode k1 = k2.mLeft;
        k2.mLeft = k1.mRight;
        k1.mRight = k2;
        // 高度计算时必须先计算子树的高度才能计算其父节点的高度，否则会因为子树高度未计算完毕导致父节点高度计算有误
        k2.height = Math.max(height(k2.mLeft), height(k2.mRight)) + 1;
        k1.height = Math.max(height(k1.mLeft), k2.height) + 1;
        return k1;
    }

    /** RR Rotation */
    private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k2){
        AvlNode k1 = k2.mRight;
        k2.mRight = k1.mLeft;
        k1.mLeft = k2;
        // 高度计算时必须先计算子树的高度才能计算其父节点的高度，否则会因为子树高度未计算完毕导致父节点高度计算有误
        k2.height = Math.max(height(k2.mLeft), height(k2.mRight)) + 1;
        k1.height = Math.max(k2.height, height(k1.mRight)) + 1;
        return k1;
    }

    /** LR Rotation */
    private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3){
        // 先将左子树与其下的左子树进行右单旋，然后再将旋转后的子树与 k3 进行左单旋
        k3.mLeft = rotateWithRightChild(k3.mLeft);
        return rotateWithLeftChild(k3);
    }

    /** RL Rotation */
    private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k3){
        // 先将右子树与其下的右子树进行左单旋，然后再将旋转后的子树与 k3 进行右单旋
        k3.mRight = rotateWithLeftChild(k3.mRight);
        return rotateWithRightChild(k3);
    }
}
