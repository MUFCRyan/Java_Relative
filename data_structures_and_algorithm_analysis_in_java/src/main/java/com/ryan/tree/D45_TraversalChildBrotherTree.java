package com.ryan.tree;

import com.ryan.util.PrintUtil;

/**
 * Created by zhaofengchun on 2017/11/2.
 * 练习 4.45 编写一个例程遍历儿子/兄弟链存储的树
 */

class D45_TraversalChildBrotherTree<AnyType extends Comparable<AnyType>> {

    private static class ChildBrotherNode<AnyType extends Comparable<AnyType>> {
        private ChildBrotherNode<AnyType> child;
        private ChildBrotherNode<AnyType> brother;
        private AnyType element;
    }


    private void traversal(ChildBrotherNode<AnyType> tree) {
        if (tree == null)
            return;
        PrintUtil.println(tree.element);
        traversal(tree.child);
        traversal(tree.brother);
    }
}
