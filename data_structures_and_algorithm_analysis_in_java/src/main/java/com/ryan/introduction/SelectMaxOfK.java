package com.ryan.introduction;

import java.util.Arrays;

/**
 * Created by MUFCRyan on 2017/10/11.
 * N 个数中选择第 k 个最大数
 */

public class SelectMaxOfK {

    public static void main(String[] args){
        int[] arr = new int[]{3,2,1,9,0,0,0,0,0,10,11,8};
        long start = System.currentTimeMillis();
        int ka = selectK(arr, 1);
        System.out.println("time:"+(System.currentTimeMillis()-start));
        System.out.println(Arrays.toString(arr));
        System.out.println(ka);
    }

    public static int selectK(int[] array, int k){
        // 创建一个 k 大小的数组 spareArray，并将原数组 array 的前k个数读入
        int[] spareArray = new int[k];
        for (int i = 0; i < k; i++) {
            spareArray[i] = array[i];
        }

        // 对 spareArray 内部进行倒序的排序
        spareArray = sort(spareArray);

        // 从 k+1 个数开始对 array 进行循环
        for (int i = k; i < array.length; i++) {
            // 循环比较 array 中的第 k+1 及以后的数
            int number = array[i];
            if (spareArray[k - 1] < number){
                // 若 array 中的数比 spareArray 的第 K 个数大，就 array 中的数插入并重新排序
                insert(spareArray, number);
            }
        }

        // 返回 spareArray 的第 k 个数的值
        return spareArray[k - 1];
    }

    private static void insert(int[] array, int number) {
        if (array.length == 1 && number > array[0]){
            array[0] = number;
            return;
        }

        for (int outer = 0; outer < array.length - 1; outer++) {
            if (array[outer] >= number && number > array[outer + 1]
                    || ((outer == 0) && array[outer] < number)){
                for (int inner = array.length - 2; inner >= outer; inner--) {
                    array[inner + 1] = array[inner];
                }
                if (outer == 0)
                    array[outer] = number;
                else
                    array[outer + 1] = number;
                break;
            }
        }
    }

    private static int[] sort(int[] array) {
        if (array.length < 1)
            return array;
        for (int outer = 0; outer < array.length - 1; outer++) {
            for (int inner = 0; inner < array.length - 1 - outer; inner++) {
                if (array[inner] < array[inner + 1]){
                    int temp = array[inner];
                    array[inner] = array[inner + 1];
                    array[inner + 1] = temp;
                }
            }
        }
        return array;
    }
}
