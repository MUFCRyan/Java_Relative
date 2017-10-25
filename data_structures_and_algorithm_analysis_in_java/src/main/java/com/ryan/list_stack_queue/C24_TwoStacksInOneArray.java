package com.ryan.list_stack_queue;

import com.ryan.util.PrintUtil;

/**
 * Created by zhaofengchun on 2017/10/25.
 * 练习 3.24 编写一个只用数组而实现两个栈的例程。这些例程不应该声明溢出，除非数组中的每个单元都被使用
 */

public class C24_TwoStacksInOneArray {
    private static final int TYPE_STACK_A = 0;
    private static final int TYPE_STACK_B = 1;
    private static final int[] sStackArray = new int[10];
    private static int sATopPoint = -1;
    private static int sBTopPoint = sStackArray.length;
    private static int TEST_A_N = 5;
    private static int TEST_B_N = 6;

    public static void main(String[] args){
        for (int i = 0; i < TEST_A_N; i++) {
            enqueue(TYPE_STACK_A, i);
        }

        for (int i = 0; i < TEST_B_N; i++) {
            enqueue(TYPE_STACK_B, i + 5);
        }

        for (int i = 0; i < TEST_A_N; i++) {
            dequeue(TYPE_STACK_A);
        }

        for (int i = 0; i < TEST_B_N; i++) {
            dequeue(TYPE_STACK_B);
        }
    }

    private static boolean enqueue(int stackType, int value){
        if (sATopPoint + 1 == sBTopPoint){
            switch(stackType){
                case TYPE_STACK_A:
                    PrintUtil.println("Stack A is full, cannot add again!");
                    break;
                case TYPE_STACK_B:
                    PrintUtil.println("Stack B is full, cannot add again!");
                    break;
            }
            return false;
        }
        switch(stackType){
            case TYPE_STACK_A:
                sATopPoint ++;
                sStackArray[sATopPoint] = value;
                return true;
            case TYPE_STACK_B:
                sBTopPoint --;
                sStackArray[sBTopPoint] = value;
                return true;
        }
        return false;
    }

    private static boolean dequeue(int stackType){
        switch(stackType){
            case TYPE_STACK_A:
                if (sATopPoint == -1){
                    PrintUtil.println("Stack A is null, cannot delete again!");
                    return false;
                }
                sStackArray[sATopPoint] = -1;
                sATopPoint --;
                return true;
            case TYPE_STACK_B:
                if (sBTopPoint == sStackArray.length){
                    PrintUtil.println("Stack B is null, cannot delete again!");
                    return false;
                }
                sStackArray[sBTopPoint] = -1;
                sBTopPoint ++;
                return true;
        }
        return false;
    }
}
