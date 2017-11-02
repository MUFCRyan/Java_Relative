package com.ryan.tree;

/**
 * Created by MUFCRyan on 2017/11/2.
 * 练习 4.36 编写一个方法，使它生成一棵具有关键字从1~2^(h+1)-1且高为 h 的理想平衡二叉查找树。该方法的运行时间是多少？
 */

public class D36_PerfectlyAVL {
    public static void main(String[] args){
        D36_PerfectlyAVL perfectlyAVL = new D36_PerfectlyAVL();
        Node<Integer> tree = perfectlyAVL.getTree(3);
    }

    private Node<Integer> getTree(int height){
        int N = (int) (Math.pow(2, height + 1) - 1); // 节点总数
        int minNum = 1; // 节点最小值
        return getTree(minNum, N, height);
    }

    private Node<Integer> getTree(int leftSideNum, int rightSideNum, int height){
        Node<Integer> node = null;
        if (height >= 0){
            int rootNum = (leftSideNum + rightSideNum) / 2;
            node = new Node<>(null, null, null, rootNum);
            node.setHeight(height);
            node.left = getTree(leftSideNum, rootNum - 1, height - 1);
            node.right = getTree(rootNum + 1, rightSideNum, height - 1);
        }
        return node;
    }
}
