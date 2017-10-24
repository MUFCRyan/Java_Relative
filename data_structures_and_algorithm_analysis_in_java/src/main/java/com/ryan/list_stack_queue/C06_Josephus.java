package com.ryan.list_stack_queue;

import com.ryan.util.PrintUtil;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by zhaofengchun on 2017/10/23.
 * 练习 3.6 约瑟夫问题，总数为 N，报数为 M
 * a. 编写一个程序解决 M 和 N 在一般下的约瑟夫问题，应使程序尽可能的高效率，要确保能够清楚各个单元
 * b. 你的程序的运行时间是多少?
 */

public class C06_Josephus {
    public static void main(String[] args){
        //josephus(5, 2);
        pass(5, 2);
    }

    private static void josephus(int n, int m){
        int index = m;
        while (n > 0){
            if (index > n){
                index = index % n;
            }
            PrintUtil.println(index);
            index += m;
            n --;
        }
    }

    private static void pass(int n, int m){
        int i, j, mPrime, numLeft;
        ArrayList<Integer> list = new ArrayList<>();
        for (i = 1; i <= n; i++) {
            list.add(i);
        }

        ListIterator<Integer> iterator = list.listIterator();
        Integer item = 0;
        numLeft = n;
        list.trimToSize();

        for (i = 0; i < n; i++) {
            mPrime = m % numLeft;
            if (mPrime <= numLeft / 2){
                if (iterator.hasNext())
                    item = iterator.next();
                for (j = 0; j < mPrime; j++) {
                    if (!iterator.hasNext())
                        iterator = list.listIterator();
                    item = iterator.next();
                }
            } else {
                for (j = 0; j < numLeft - mPrime; j++) {
                    if (!iterator.hasPrevious())
                        iterator = list.listIterator(list.size());
                    item = iterator.previous();
                }
            }

            PrintUtil.print("Removed " + item + " ");
            iterator.remove();
            if (!iterator.hasNext()){
                iterator = list.listIterator();
            }
            PrintUtil.println();
            for (Integer x : list) {
                PrintUtil.print(x + " ");
            }
            PrintUtil.println();
            numLeft --;
        }
        PrintUtil.println();
    }
}
