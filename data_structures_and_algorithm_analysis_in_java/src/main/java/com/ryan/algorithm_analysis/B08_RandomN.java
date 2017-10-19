package com.ryan.algorithm_analysis;

import com.ryan.util.PrintUtil;

import java.util.Random;

/**
 * Created by zhaofengchun on 2017/10/17.
 *
 */

public class B08_RandomN {

    private static Random sRandom = new Random();

    public static void main(String[] args){
        //PrintUtil.println("Run Time: " + new RandomGenerator1().run(0, 8000));
        //PrintUtil.println("Run Time: " + new RandomGenerator2().run(0, 400000));
        PrintUtil.println("Run Time: " + new RandomGenerator3().run(0, RandomGenerator3.N));
    }

    static abstract class RandomGenerator{
        static int N = 0;
        static Random sRandom = new Random();

        static void setN(int n){
            N = n;
        }

        double run(int i, int j){
            long start = System.currentTimeMillis();
            for (int index = 0; index < 10; index++) {
                //PrintUtil.println("Run Count - " + (index + 1) + ": " +Arrays.toString(generateArray(i, j)));
                generateArray(i, j);
            }
            return ((double) (System.currentTimeMillis() - start));
        }

        int randomInt(int i, int j){
            return sRandom.nextInt(j - i + 1) + i;
        }

        abstract int[] generateArray(int min, int max);
    }

    static class RandomGenerator1 extends RandomGenerator{
        // 理论：O(N^2) + O(while)
        // 实际：O(N^2)
        // 实际时长（N-ms*10:avg）：
        // 250 - 13:
        // 500 - 21:
        // 1000 - 42:
        // 2000 - 109:
        // 8000 - 1843:
        @Override
        int[] generateArray(int min, int max) {
            int[] array = new int[max - min + 1];
            for (int index = 0; index < array.length; index++) {
                array[index] = generateUnRepeatNumber(array, index, min, max);
            }
            return array;
        }

        private int generateUnRepeatNumber(int[] array, int end, int min, int max) {
            while (true){
                int randomInt  = randomInt(min, max);
                if (end == 0)
                    return randomInt;
                for (int i = 0; i < end; i++) {
                    if (array[i] == randomInt){
                        break;
                    }
                    if (i == end - 1 && array[i] != randomInt){
                        return randomInt;
                    }
                }
            }
        }
    }

    static class RandomGenerator2 extends RandomGenerator{
        // 算法2，使用一个 usedArray 保存用过的整数
        // 理论：O(N) + O(while)
        // 实际：O(N) + O(while)
        // 实际时长（N-ms*10:avg）：
        // 25000 - 51:
        // 50000 - 118
        // 100000 - 306
        // 200000 - 489
        // 400000 - 1212
        // 800000 - 2208
        @Override
        int[] generateArray(int min, int max) {
            int[] array = new int[max - min + 1];
            boolean[] usedArray = new boolean[max - min + 1];
            for (int index = 0; index < array.length; index++) {
                array[index] = generateUnRepeatNumber(usedArray, min, max);
            }
            return array;
        }

        private int generateUnRepeatNumber(boolean[] usedArray, int min, int max) {
            int transferLength = 0 - min; // 实际数字大小与其所在 UsedArray 上的位置的转换差值
            while (true){
                int randomInt = randomInt(min, max);
                if (!usedArray[randomInt + transferLength]){
                    usedArray[randomInt + transferLength] = true;
                    return randomInt;
                }
            }
        }
    }

    static class RandomGenerator3 extends RandomGenerator{
        // 算法3，用 a[i] = i + 1 填充整个数组，然后循环一遍后随机替换
        // 理论：O(N)
        // 实际：O(N)
        // 实际时长（N-ms*10:avg）：
        // 100000 - 43
        // 200000 - 91
        // 400000 - 225
        // 800000 - 480
        // 1600000 - 1023
        // 3200000 - 1959
        // 6400000 - 4038

        RandomGenerator3(){
            setN(6400000);
        }

        @Override
        int[] generateArray(int min, int max) {
            int[] array = new int[max - min + 1];
            int transferLength = 0 - min;
            for (int index = 0; index < array.length; index++) {
                array[index] = index + 1 + transferLength;
            }

            for (int i = 0; i < array.length; i++) {
                swapReferences(array, i, randomInt(min, max) + transferLength);
            }

            return array;
        }

        private void swapReferences(int[] array, int index, int randomIndex) {
            int temp = array[index];
            array[index] = array[randomIndex];
            array[randomIndex] = temp;
        }
    }
}