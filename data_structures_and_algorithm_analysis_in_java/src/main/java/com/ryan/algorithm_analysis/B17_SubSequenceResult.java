package com.ryan.algorithm_analysis;

import com.ryan.util.PrintUtil;

/**
 * Created by MUFCRyan on 2017/10/20.
 * 练习 2.17 给出有效的算法（及其运行时间分析）
 * a. 求最小子序列和
 * *b. 求最小的正子序列和
 * *c. 求最大子序列乘积
 */

public class B17_SubSequenceResult {
    //private static final int[] sSequence = new int[]{-15, -8, 9, 6, -5, -3, 0, -1, -8, 3, 4, -3,7, 12, 0, 18, -22};
    private static final int[] sSequence = new int[]{-15, -8, 9, -5, -1, -2};

    public static void main(String[] args) {
        //PrintUtil.println(new MinSubSequenceSum().calculate(sSequence));
        //PrintUtil.println(new MinPositiveSubSequenceSum().calculate(sSequence));
        PrintUtil.println(new MaxSubSequenceMultiple().calculate(sSequence));
    }

    interface SubSequenceCalculate {
        int calculate(int[] sequence);
    }

    /** a. 求最小子序列和 */
    private static class MinSubSequenceSum implements SubSequenceCalculate {

        @Override
        public int calculate(int[] sequence) {
            int minSum = 0;
            int currentSum = 0;
            for (int i = 0; i < sequence.length; i++) {
                currentSum += sequence[i];
                if (currentSum < minSum)
                    minSum = currentSum;
                else if (currentSum > 0){
                    currentSum = 0;
                }
            }
            return minSum;
        }
    }

    /** *b. 求最小的正子序列和 */
    private static class MinPositiveSubSequenceSum implements SubSequenceCalculate {

        @Override
        public int calculate(int[] sequence) {
            int minPositiveSum = Integer.MAX_VALUE;
            int currentSum = 0;
            // 正序遍历
            for (int i = 0; i < sequence.length; i++) {
                currentSum += sequence[i];
                if (0 < currentSum && currentSum < minPositiveSum)
                    minPositiveSum = currentSum;
                else if (currentSum < 0){ // 关键条件：当前累加和
                    currentSum = 0;
                }
            }

            // 倒序遍历
            for (int i = sequence.length - 1; i >= 0; i--) {
                currentSum += sequence[i];
                if (0 < currentSum && currentSum < minPositiveSum)
                    minPositiveSum = currentSum;
                else if (currentSum < 0){ // 关键条件：当前累加和
                    currentSum = 0;
                }
            }
            return minPositiveSum;
        }
    }

    /** *c. 求最大子序列乘积 */
    private static class MaxSubSequenceMultiple implements SubSequenceCalculate {

        @Override
        public int calculate(int[] sequence) {
            int currentMultiple;
            int realMax = 0;
            int maxMultiple = sequence[0];
            int minMultiple = sequence[0];
            for (int i = 1; i < sequence.length; i++) {
                currentMultiple = maxMultiple;
                // 求出当前整数、当前整数乘以累乘值、当前整数乘以最小累乘值（因为要考虑负负得正的情况）中的最大值
                int currentElement = sequence[i];
                maxMultiple = Math.max(Math.max(currentElement, currentMultiple * currentElement), minMultiple * currentElement);
                // 求出当前整数、当前整数乘以累乘值、当前整数乘以最大累乘值（因为要考虑负负得正的情况）中的最小值
                minMultiple = Math.min(Math.min(currentElement, currentMultiple * currentElement), minMultiple * currentElement);
                if (maxMultiple > realMax)
                    realMax = maxMultiple;
            }
            return realMax;
        }
    }
}
