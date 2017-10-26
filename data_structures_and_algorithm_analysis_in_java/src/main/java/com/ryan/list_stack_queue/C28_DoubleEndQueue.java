package com.ryan.list_stack_queue;

import com.ryan.util.PrintUtil;

/**
 * Created by MUFCRyan on 2017/10/26.
 * 练习 3.28 双端队列是由一列项组成的数据结构，对该数据结构可进行下列操作：
 * push(x)：将项 x 插入到队列的前端。
 * pop()：从双端队列中删除前端项并将其返回。
 * inject(x)：将项 x 插入到双端队列的尾端。
 * eject()：从双端队列中删除尾端项并将其返回。
 * 编写支持双端队列的例程，其中每种操作均花费 O(1) 的时间
 *
 *
 * 方案1：定义数组分别从头尾添加和删除
 * 方案2：使用 LinkedList，分别将其 addFirst/removeFirst/addLast/removeLast 方法封装为 push/pop/inject/eject，存疑？这样的操作貌似不符合队列的先进先出原则
 */

public class C28_DoubleEndQueue {
    private static final int QUEUE_SIZE = 10;
    private static int[] sQueue;
    private static int sForwardStartIndex = -1;
    private static int sBackwardStartIndex = QUEUE_SIZE - 1;
    private static int sForwardEndIndex = -1;
    private static int sBackwardEndIndex = QUEUE_SIZE - 1;

    static {
        sQueue = new int[QUEUE_SIZE];
    }

    private static void push(int x){
        if (sForwardEndIndex + 1 == sBackwardEndIndex){
            PrintUtil.println("Queue forward is full, cannot add again!");
        }else {
            sForwardEndIndex++;
            sQueue[sForwardEndIndex] = x;
            if (sForwardStartIndex == -1)
                sForwardStartIndex = 0;
        }
    }

    private static void inject(int x){
        if (sForwardEndIndex + 1 == sBackwardEndIndex){
            PrintUtil.println("Queue backward is full, cannot add again!");
        } else {
            sBackwardEndIndex--;
            sQueue[sForwardEndIndex] = x;
            if (sBackwardStartIndex == QUEUE_SIZE)
                sBackwardStartIndex = QUEUE_SIZE - 1;
        }
    }

    private static int pop(){
        if (sForwardEndIndex == sForwardStartIndex){
            PrintUtil.println("Queue forward is empty, cannot delete again!");
            return -1;
        }
        int x = sQueue[sForwardStartIndex];
        sForwardStartIndex++;
        return x;
    }

    private static int eject(){
        if (sBackwardEndIndex == sBackwardStartIndex){
            PrintUtil.println("Queue backward is empty, cannot delete again!");
            return -1;
        }
        int x = sQueue[sBackwardStartIndex];
        sBackwardStartIndex --;
        return x;
    }
}
