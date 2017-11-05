package com.ryan.hash;

/**
 * Created by zhaofengchun on 2017/11/5.
 * 练习 5.3 编写一个程序，计算使用线性探测、平方探测、双散列时的长随机插入序列中所需的冲突次数
 */

public class E03_CalculateCollisions {
    private int linearProbingCollisions = 0;
    private int squareProbingCollisions = 0;
    private int doubleHashCollisions = 0;
    private ElementType[] mArray = new ElementType[1000];

    private int linearProbing(ElementType element) {
        int position = hash(element.element, mArray.length);
        while (mArray[position] != null && mArray[position].isActivated) {
            linearProbingCollisions++;
            position = nextPosition(position);
        }
        element.isActivated = true;
        mArray[position] = element;
        return linearProbingCollisions;
    }

    private int squareProbing(ElementType element, int hash) {
        int position = hash;
        int tempCollisions = 0;
        while (mArray[position] != null && mArray[position].isActivated) {
            tempCollisions++;
            position = nextPosition(position);
        }
        squareProbingCollisions += tempCollisions;
        position = (int) (position + (Math.pow(tempCollisions, 2) - 1));
        position = nextPosition(position);
        if (mArray[position] == null || !mArray[position].isActivated) {
            element.isActivated = true;
            mArray[position] = element;
            return squareProbingCollisions;
        }
        return squareProbing(element, position);
    }

    private int doubleHashing(ElementType element){
        int position = hash(element.element, mArray.length);
        int hash2 = hash2(element.element);
        while (mArray[position] != null && mArray[position].isActivated){
            doubleHashCollisions ++;
            position += hash2;
        }
        element.isActivated = true;
        mArray[position] = element;
        return doubleHashCollisions;
    }

    private int hash(int num, int tableSize) {
        return num % tableSize;
    }

    private int hash2(int num) {
        return 7 - (num % 7);
    }

    private int nextPosition(int currentPosition) {
        int position = currentPosition;
        position++;
        if (position > mArray.length)
            position -= mArray.length;
        if (position < 0)
            position += mArray.length;
        return position;
    }

    private boolean isPrime(int num) {
        int result = (int) Math.sqrt(num);
        for (int i = 0; i < result; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    private int nextPrime(int num) {
        int i = num;
        while (!isPrime(i)) {
            if (i % 2 == 0)
                i++;
            else
                i += 2;
        }
        return i;
    }
}
