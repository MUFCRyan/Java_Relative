package com.ryan.tree;

/**
 * Created by MUFCRyan on 2017/11/2.
 * 练习 4.31 编写一些高效的方法，只使用对二叉树的根的引用 T，并计算：
 *      a. T 中节点的个数
 *      b. T 中叶节点个数
 *      c. T 中满节点的个数
 */

public class D31_CalculateNodes<AnyType extends Comparable<? super AnyType>>{
    int countNodes(Node<AnyType> node){
        if (node == null)
            return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    private int countLeafNodes(Node<AnyType> node){
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;
        return countLeafNodes(node.left) + countLeafNodes(node.right);
    }

    private int countFullNodes(Node<AnyType> node){
        if (node == null)
            return 0;
        if (node.left != null && node.right != null){
            return 1 + countFullNodes(node.left) + countFullNodes(node.right);
        } else {
            return countFullNodes(node.left) + countFullNodes(node.right);
        }
    }
}
