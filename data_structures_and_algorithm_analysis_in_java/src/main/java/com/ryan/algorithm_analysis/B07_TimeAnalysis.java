package com.ryan.algorithm_analysis;

import com.ryan.util.PrintUtil;

/**
 * Created by MUFCRyan on 2017/10/17.
 * 对下列六个程序片段中的每一个：
 *      a. 给出运行时间分析（大 O）
 *      b. Java 编程，对 N 的若干具体指给出运行时间
 *      c. 用实际运行时间与所做的分析值进行比较
 */

public class B07_TimeAnalysis {
    public static void main(String[] args){
        //PrintUtil.println(new Test1().run(9999999));
        //PrintUtil.println(new Test2().run(10000));
        //PrintUtil.println(new Test3().run(2000));
        //PrintUtil.println(new Test4().run(5000));
        //PrintUtil.println(new Test5().run(100));
        PrintUtil.println(new Test6().run(200));
    }

    static class Test{
        public double run(long number){
            long start = System.currentTimeMillis();
            function(number);
            return ((double) (System.currentTimeMillis() - start));
        }

        protected void function(long number){

        }
    }

    static class Test1 extends Test{
        // 分析：O(N)
        // 实际：O(N)
        @Override
        protected void function(long number) {
            int sum  = 0;
            for (int i = 0; i < number; i++) {
                sum++;
            }
        }
    }

    static class Test2 extends Test{
        // 分析：O(N^2)
        // 实际：< O(N^2)
        @Override
        protected void function(long number) {
            int sum  = 0;
            for (int i = 0; i < number; i++) {
                for (long j = 0; j < number; j++) {
                    sum++;
                }
            }
        }
    }

    static class Test3 extends Test{
        // 分析：O(N^3)
        // 实际：O(N^3)
        @Override
        protected void function(long number) {
            int sum  = 0;
            for (int i = 0; i < number; i++) {
                for (long j = 0; j < number * number; j++) {
                    sum++;
                }
            }
        }
    }

    static class Test4 extends Test{
        // 分析：O(N^2)
        // 实际：< O(N^2)
        @Override
        protected void function(long number) {
            int sum  = 0;
            for (int i = 0; i < number; i++) {
                for (long j = 0; j < i; j++) {
                    sum++;
                }
            }
        }
    }

    static class Test5 extends Test{
        // 分析：O(N^4)
        // 实际：< O(N^4)
        @Override
        protected void function(long number) {
            int sum  = 0;
            for (int i = 0; i < number; i++) {
                for (long j = 0; j < i * i; j++) {
                    for (long k = 0; k < j; k++) {
                        sum++;
                    }
                }
            }
        }
    }

    static class Test6 extends Test{
        // 分析：O(N^4)
        // 实际：< O(N^4)
        @Override
        protected void function(long number) {
            int sum  = 0;
            for (int i = 0; i < number; i++) {
                for (int j = 0; j < i * i; j++) {
                    if (j % i == 0){
                        for (int k = 0; k < j; k++) {
                            sum++;
                        }
                    }
                }
            }
        }
    }
}
