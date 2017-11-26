package com.ryan.sort;

import com.ryan.util.PrintUtil;

import java.util.Arrays;

import static com.ryan.sort.Sort.sItems;


/**
 * Created by zhaofengchun on 2017/11/25.
 * 练习：7.14 重写堆排序，使得只对 low 到 high 范围的项进行排序，其中 low 和 high 作为附加参数被传递
 */

public class G14_HeapSort {
    public static void main(String[] args){
        heapSort(sItems, 2, sItems.length - 2);
    }

    /**
     * Internal method of heap sort
     * @param i the index of an item in the heap
     * @return the index of the left child
     */
    private static int leftChild(int i, int low){
        return 2 * (i - low) + 1 + low;
    }

    /**
     * Internal method for heap sort that is used in deleteMax and buildHeap.
     * @param a an array of Comparable items
     * @param i the position from which to percolate down
     * @param n the logical size of the heap
     */
    private static <AnyType extends Comparable<? super AnyType>> void percolateDown(AnyType[] a, int i, int n, int low, int high){
        int child;
        AnyType temp;
        for (temp = a[i]; leftChild(i, low) < n; i = child) {
            child = leftChild(i, low);
            if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0)
                child ++;
            if (temp.compareTo(a[child]) < 0)
                a[i] = a[child];
            else
                break;
        }
        a[i] = temp;
    }

    /**
     * Another heap sort, the range of sort is between low to high
     * @param a an array of Comparable items
     */
    private static <AnyType extends Comparable<? super AnyType>> void heapSort(AnyType[] a, int low, int high){
        /** buildHeap */
        for (int i = (high - low) / 2 + low ; i >= low; i--) {
            percolateDown(a, i, high, low, high);
        }
        /** deleteMax */
        for (int i = high - 1; i > low; i--) {
            swapReferences(a, low, i);
            percolateDown(a, low, i, low, high);
        }
        PrintUtil.println(Arrays.asList(a));
    }

    private static<AnyType extends Comparable<? super AnyType>>  void swapReferences(AnyType[] a, int i1, int i2) {
        AnyType temp = a[i1];
        a[i1] = a[i2];
        a[i2] = temp;
    }
}
