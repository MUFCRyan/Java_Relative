package com.ryan.sort;

import static com.ryan.sort.Sort.CUTOFF;
import static com.ryan.sort.Sort.insertionSort;
import static com.ryan.sort.Sort.median3;
import static com.ryan.sort.Sort.swapReferences;

/**
 * Created by zhaofengchun on 2017/11/26.
 *
 */

public class G25_QucikSort {

    private static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a, int left, int right) {
        if (left + CUTOFF < right) {
            // 选取枢纽元
            AnyType pivot = median3(a, left, right);
            // 开始分区
            int i = left, j = right - 1;
            for (; ; ) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--i].compareTo(pivot) > 0) {
                }
                if (i < j) {
                    swapReferences(a, i, j);
                } else {
                    break;
                }
            }
            // 回复枢纽元的位置
            swapReferences(a, i, right - 1);
            // 排序小于枢纽元的元素
            quickSort(a, left, i - 1);
            // 排序大于枢纽元的元素
            quickSort(a, i + 1, right);
        } else {
            // 使用插入排序
            insertionSort(a);
        }
    }
}
