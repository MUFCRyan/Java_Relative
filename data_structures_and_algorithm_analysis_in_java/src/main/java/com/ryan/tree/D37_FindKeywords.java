package com.ryan.tree;

import com.ryan.util.PrintUtil;

/**
 * Created by zhaofengchun on 2017/11/2.
 * 练习 4.37 编写一个方法以二叉查找树 T 和两个有序的关键字 k1 和 k2 作为输入，其中 k1 <= k2，并打印树中所有满足 k1 <= Key(X) <= k2 的元素 X。
 * 出可以被排序外，不对关键字的类型做任何假设。缩写的程序应该以平均时间 O(K + logN) 运行，其中 K 是所打印的关键字的个数。确定你的算法的运行时间界。
 */

public class D37_FindKeywords<AnyType extends Comparable<AnyType>> {

    private void printKeywords(Node<AnyType> root, AnyType k1, AnyType k2) {
        if (root != null) {

            // 寻找合适的元素平均耗费 logN

            if (root.element.compareTo(k1) >= 0) {
                printKeywords(root.left, k1, k2);
            }

            if (root.element.compareTo(k1) >= 0 && root.element.compareTo(k2) <= 0) {
                PrintUtil.println(root.element); // K 个关键字耗时 K 个单位
            }

            if (root.element.compareTo(k2) <= 0){
                printKeywords(root.right, k1, k2);
            }

            // O(K + logN)
        }
    }
}