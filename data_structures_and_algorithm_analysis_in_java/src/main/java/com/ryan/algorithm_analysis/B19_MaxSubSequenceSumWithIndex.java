package com.ryan.algorithm_analysis;

import com.ryan.util.PrintUtil;

import java.util.Arrays;

/**
 * Created by MUFCRyan on 2017/10/20.
 * 练习 2.19 以单个对象的形式返回最大子序列的值及具体子序列的下标值
 */

public class B19_MaxSubSequenceSumWithIndex {
    private static final int[] sSequence = new int[]{-15, -8, 9, -5, -1, -2};
    public static void main(String[] args){
        PrintUtil.println(getMaxSubSequence(sSequence));
    }

    private static MaxSubSequence getMaxSubSequence(int[] sequence){
        MaxSubSequence maxSubSequence = new MaxSubSequence();
        MaxSubSequence currentSubSequence = new MaxSubSequence();
        int currentSum = 0;
        int maxSum = 0;
        maxSubSequence.setStartIndex(0);
        currentSubSequence.setStartIndex(0);
        for (int i = 0; i < sequence.length; i++) {
            currentSum += sequence[i];

            if (!currentSubSequence.isStartIndexValid()){
                currentSubSequence.setStartIndex(i);
            }
            currentSubSequence.setEndIndex(i);

            if (currentSum > maxSum){
                maxSum = currentSum;
                maxSubSequence.setStartIndex(currentSubSequence.getStartIndex());
                maxSubSequence.setEndIndex(currentSubSequence.getEndIndex());
            } else if (currentSum < 0){
                currentSubSequence.initStartIndex();
                currentSubSequence.initEndIndex();
                currentSum = 0;
            }
        }
        int[] maxSubSequenceArray = new int[maxSubSequence.getEndIndex() - maxSubSequence.getStartIndex() + 1];
        for (int i = maxSubSequence.getStartIndex(); i <= maxSubSequence.getEndIndex(); i++) {
            maxSubSequenceArray[i - maxSubSequence.getStartIndex()] = sequence[i];
        }
        maxSubSequence.setMaxSequence(maxSubSequenceArray);
        maxSubSequence.setMaxSubSequenceSum(maxSum);
        return maxSubSequence;
    }

    private static class MaxSubSequence{
        private int[] mMaxSequence = new int[0];
        private int mMaxSubSequenceSum;
        private int mStartIndex = -1;
        private int mEndIndex = -1;

        private void setMaxSequence(int[] maxSequence) {
            mMaxSequence = maxSequence;
        }

        private void setMaxSubSequenceSum(int maxSubSequenceSum) {
            mMaxSubSequenceSum = maxSubSequenceSum;
        }

        private void setStartIndex(int startIndex) {
            this.mStartIndex = startIndex;
        }

        private void setEndIndex(int endIndex) {
            this.mEndIndex = endIndex;
        }

        private int getStartIndex() {
            return mStartIndex;
        }

        private int getEndIndex() {
            return mEndIndex;
        }

        private boolean isStartIndexValid() {
            return mStartIndex != -1;
        }

        public boolean isEndIndexValid() {
            return mEndIndex != -1;
        }

        private void initStartIndex() {
            this.mStartIndex = -1;
        }

        private void initEndIndex() {
            this.mEndIndex = -1;
        }

        @Override
        public String toString() {
            return Arrays.toString(mMaxSequence);
        }
    }
}
