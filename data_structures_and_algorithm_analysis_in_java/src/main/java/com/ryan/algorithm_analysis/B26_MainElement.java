package com.ryan.algorithm_analysis;

import com.ryan.util.PrintUtil;

/**
 * Created by zhaofengchun on 2017/10/22.
 * 练习 2.26 获取数组中的主元素
 */

public class B26_MainElement {
    private static int[] sArray = new int[]{3,3,4,2,4,4,2,4,4,3,3,3,3,3,3,3,3};
    public static void main(String[] args) {
        PrintUtil.println(getMainElement(sArray));
    }

    private static int getMainElement(int[] array) {
        int mainElement = -1;
        if (array == null || array.length == 0)
            return mainElement;
        if (array.length == 1)
            return array[0];
        int[] preparedArray = new int[array.length / 2];
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == array[i + 1]) {
                preparedArray[count] = array[i];
                count ++;
                i++; // 复合条件时就将指针向后主动移动一位，for 循环本身会再次移动一位，相当于移动两位，这样就可以跳过这两个比较的元素，免去对第二个元素的不必要的比较
            }
        }
        array = new int[count];
        for (int i = 0; i < array.length; i++) {
            array[i] = preparedArray[i];
        }
        return getMainElement(array);
    }
}
