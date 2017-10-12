package com.ryan.introduction;

/**
 * Created by MUFCRyan on 2017/10/12.
 * 计算一个数的二进制数中 1 的个数
 */

public class BinaryOneCount {
    private static final int NUMBER = 123;
    private static int count = 0;
    public static void main(String[] args){
        BinaryOneCount binaryOneCount = new BinaryOneCount();
        if (NUMBER % 2 == 1){
            binaryOneCount.calculateOneCount(NUMBER / 2);
            count ++;
        } else {
            binaryOneCount.calculateOneCount(NUMBER);
        }
        System.out.println(count);
    }

    int calculateOneCount(int n){
        if (n > 0){
            if (n % 2 == 1){
                count ++;
            }
            return calculateOneCount(n / 2);
        }
        return n;
    }
}
