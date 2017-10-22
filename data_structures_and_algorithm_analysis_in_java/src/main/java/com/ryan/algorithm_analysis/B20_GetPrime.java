package com.ryan.algorithm_analysis;

import com.ryan.util.PrintUtil;

/**
 * Created by MUFCRyan on 2017/10/20.
 * 练习 2.20
 * a. 编写一个程序来确定 N 是否是素数
 * b. 你的程序在最坏的情况下的运行时间是多少(N 表示)？(你应该能以 N^(1/2) 来完成这项工作)
 * c. 令 B 等于 N 的二进制表示法中的位数。B 的值是多少？ B = log N
 * d. 你的程序在最坏情况下的运行时间是什么（用 B 表示）？ O(2^(B/2))
 * e. 比较确定一个20（二进制）位的数是否是素数和确定一个40（二进制）位的数是否是素数的运行时间：后者是前者运行时间的平方
 * f. 用 N 或 B 给出运行时间更合理吗？为什么？  B 更合理，因为 B 能更准确的反映出输入数据量的大小
 */

public class B20_GetPrime {
    public static void main(String[] args){
        //PrintUtil.println(isPrime1(11));
        //PrintUtil.println(isPrime2(15));
        PrintUtil.println(getBinaryDigit(8));
    }

    // 复杂度：logN
    private static boolean isPrime1(int n){
        int remainder = n / 2;
        while (remainder > 1){ // 当被余数大于1时才循环
            if (n % remainder == 0){
                return false; // 一旦 N 被大于1的整数整除，代表 N 非素数
            }
            // 折半对比判断 N 能否被整除，折半的值四舍五入
            remainder = (int) (((float)remainder / 2f) + 0.5f);
        }
        return true;
    }

    // 复杂度：n^(1/2)
    private static boolean isPrime2(long n){
        if (n == 0)
            return false;

        if (n == 1)
            return true;

        if (n % 2 == 0)
            return false;

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    // B = O(logN)
    private static int getBinaryDigit(int n){
        if (n == 0 || n == 1)
            return 1;
        int count = 0;
        while (n > 0){
            count ++;
            n = n / 2;
        }
        return count;
    }
}
