package com.ryan.algorithm_analysis;

import com.ryan.util.PrintUtil;

/**
 * Created by zhaofengchun on 2017/10/22.
 * 练习 23. 不用递归，写出快速求幂的程序，思路：将次幂转换为二进制数，各个为 1 的部分转为对应的 10 进制数作为次幂大小来逐步计算出整个结果
 */

public class B23_GetPowerWithoutRecursion {
    public static void main(String[] args){
        PrintUtil.println(getPower(3, 30));
    }

    private static long getPower(long number, long power){
        long result = 1;
        while (power > 0){
            if (power % 2 == 1){
                result *= number;  // 当二进制为 1 的时候将当前次幂的乘积结果（number）累乘进来
            }
            power >>= 1;
            number *= number; // 对应于每个二进制位计算出相应的次幂大小（number^2, number^4, number^4...number^(2*logN)），供必要时 result的使用
        }
        return result;
    }
}
