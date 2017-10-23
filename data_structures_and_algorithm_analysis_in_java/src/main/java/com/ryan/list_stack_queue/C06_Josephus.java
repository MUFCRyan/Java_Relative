package com.ryan.list_stack_queue;

import com.ryan.util.PrintUtil;

/**
 * Created by zhaofengchun on 2017/10/23.
 * 练习 3.6 约瑟夫问题，总数为 N，报数为 M
 * a. 编写一个程序解决 M 和 N 在一般下的约瑟夫问题，应使程序尽可能的高效率，要确保能够清楚各个单元
 * b. 你的程序的运行时间是多少?
 */

public class C06_Josephus {
    public static void main(String[] args){

    }

    private static void josephus(int n, int m){
        for (int i = 0; i < n; i+= m) {
            if (i >= n)
                i = n % m;
            PrintUtil.println(i);
            n--;
            i--;
        }
    }
}
