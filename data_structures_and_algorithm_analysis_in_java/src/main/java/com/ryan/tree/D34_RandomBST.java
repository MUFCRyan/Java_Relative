package com.ryan.tree;

import java.util.Random;

/**
 * Created by MUFCRyan on 2017/11/2.
 * 练习 4.34 写出生成一棵 N-节点随机二叉查找树的方法，该树具有从 1 直到 N 的不同的关键字。你所编写的例程的运行时间是多少？
 *
 */

public class D34_RandomBST<AnyType extends Comparable<AnyType>> {

    private static final int N = 100;
    private static final Random sRandom = new Random();

    /** 运行时间：O(NlogN) */
    private Node<Integer> randomBST(int N){
        boolean[] array = new boolean[N + 1];
        boolean isTreeFull = false;
        Random random = new Random();
        D23_InsertAVLNoRecursion<Integer> insertAVLNoRecursion = new D23_InsertAVLNoRecursion<>();
        D31_CalculateNodes<Integer> calculateNodes = new D31_CalculateNodes<>();
        Node<Integer> root = null;
        while (!isTreeFull){
            Integer num = random.nextInt(N) + 1;
            if (array[num])
                continue;
            array[num] = true;
            root = insertAVLNoRecursion.insert(num, root); // 插入节点
            isTreeFull = calculateNodes.countNodes(root) == N; // 判断数是否已满
        }
        return root;
    }

    /** O(logN) */
    private Node<Integer> makeRandomTree(int lower, int upper){
        Node<Integer> tree = null;
        int randomValue = 0;
        if(lower <= upper){
            randomValue = randomInteger(lower, upper);
            tree = new Node<>(null, makeRandomTree(lower,randomValue - 1), makeRandomTree(randomValue + 1, upper), randomValue);
        }
        return tree;
    }

    private boolean[] mRandomArray = new boolean[N + 1];
    private int randomInteger(int lower, int upper){
        int value = sRandom.nextInt(lower) + 1 + (upper - lower);
        while (mRandomArray[value]){
            value = sRandom.nextInt(lower) + 1 + (upper - lower);
        }
        mRandomArray[value] = true;
        return value;
    }
}
