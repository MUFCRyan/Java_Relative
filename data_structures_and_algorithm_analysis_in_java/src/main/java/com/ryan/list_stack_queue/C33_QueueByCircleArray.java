package com.ryan.list_stack_queue;

import com.ryan.util.PrintUtil;

/**
 * Created by MUFCRyan on 2017/10/26.
 * 练习 3.33 使用循环数组高效实现队列类
 */

public class C33_QueueByCircleArray {

    public static void main(String[] args){
        C33_QueueByCircleArray queueByCircleArray = new C33_QueueByCircleArray();
        for (int i = 0; i < 6; i++) {
            PrintUtil.print("startIndex = " + queueByCircleArray.mStartIndex + "; endIndex = " + queueByCircleArray.mEndIndex + "   ");
            PrintUtil.println(queueByCircleArray.enqueue(i + 1));
        }

        PrintUtil.print("startIndex = " + queueByCircleArray.mStartIndex + "; endIndex = " + queueByCircleArray.mEndIndex + "   ");
        PrintUtil.println(queueByCircleArray.dequeue());
        PrintUtil.print("startIndex = " + queueByCircleArray.mStartIndex + "; endIndex = " + queueByCircleArray.mEndIndex + "   ");
        PrintUtil.println(queueByCircleArray.enqueue(7));
        PrintUtil.print("startIndex = " + queueByCircleArray.mStartIndex + "; endIndex = " + queueByCircleArray.mEndIndex + "   ");
        PrintUtil.println(queueByCircleArray.dequeue());
        PrintUtil.print("startIndex = " + queueByCircleArray.mStartIndex + "; endIndex = " + queueByCircleArray.mEndIndex + "   ");
        PrintUtil.println(queueByCircleArray.enqueue(8));
        PrintUtil.print("startIndex = " + queueByCircleArray.mStartIndex + "; endIndex = " + queueByCircleArray.mEndIndex + "   ");
        PrintUtil.println(queueByCircleArray.dequeue());
        PrintUtil.print("startIndex = " + queueByCircleArray.mStartIndex + "; endIndex = " + queueByCircleArray.mEndIndex + "   ");
        PrintUtil.println(queueByCircleArray.dequeue());
        PrintUtil.print("startIndex = " + queueByCircleArray.mStartIndex + "; endIndex = " + queueByCircleArray.mEndIndex + "   ");
        PrintUtil.println(queueByCircleArray.dequeue());
        PrintUtil.print("startIndex = " + queueByCircleArray.mStartIndex + "; endIndex = " + queueByCircleArray.mEndIndex + "   ");
        PrintUtil.println(queueByCircleArray.dequeue());
        PrintUtil.print("startIndex = " + queueByCircleArray.mStartIndex + "; endIndex = " + queueByCircleArray.mEndIndex + "   ");
        PrintUtil.println(queueByCircleArray.dequeue());
        PrintUtil.print("startIndex = " + queueByCircleArray.mStartIndex + "; endIndex = " + queueByCircleArray.mEndIndex + "   ");
        PrintUtil.println(queueByCircleArray.dequeue());
        PrintUtil.print("startIndex = " + queueByCircleArray.mStartIndex + "; endIndex = " + queueByCircleArray.mEndIndex + "   ");
        PrintUtil.println(queueByCircleArray.dequeue());
        PrintUtil.print("startIndex = " + queueByCircleArray.mStartIndex + "; endIndex = " + queueByCircleArray.mEndIndex + "   ");
        PrintUtil.println(queueByCircleArray.dequeue());
        PrintUtil.print("startIndex = " + queueByCircleArray.mStartIndex + "; endIndex = " + queueByCircleArray.mEndIndex + "   ");
    }

    private int[] mArray = new int[6];
    // 计数：endIndex 永远在 startIndex 的前面，最多二者位于同一位置，计算二者间的长度时，startIndex 顺着数组下标增大的方向找，endIndex
    // 则向下标减小的方向找
    private int mStartIndex = 0;
    private int mEndIndex = 0;


    private boolean enqueue(int x){
        // startIndex = 0，endIndex = mArray.length - 1，意味着数组已经被填满
        if (mStartIndex == 0 && mEndIndex == mArray.length - 1)
            return false;
        // mEndIndex + 1 == mStartIndex，即 mEndIndex 在 mStartIndex 后边的一位时意味着数组已经被填满
        if (generateRightIndex(mEndIndex + 1) == mStartIndex){
            return false;
        }
        mArray[mEndIndex] = x;
        mEndIndex ++;
        mEndIndex = generateRightIndex(mEndIndex);
        return true;
    }

    private int dequeue(){
        // 队列为空
        if (mStartIndex == mEndIndex)
            return -1;
        mArray[mStartIndex] = -1;
        mStartIndex ++;
        mStartIndex = generateRightIndex(mStartIndex);
        return mStartIndex;
    }

    private int generateRightIndex(int index){
        int rightIndex = index;
        if (rightIndex >= mArray.length){
            rightIndex -= mArray.length;
            return rightIndex;
        }

        if (rightIndex < 0){
            rightIndex += mArray.length;
            return rightIndex;
        }
        return rightIndex;
    }
}
