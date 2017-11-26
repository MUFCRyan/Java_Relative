package com.ryan.sort;

import com.ryan.util.PrintUtil;

import java.util.Arrays;

/**
 * Created by zhaofengchun on 2017/11/26.
 * 排序
 */

public class Sort {
    static Integer[] sItems = new Integer[]{
            4, 6, 8, 2, 1, 243, 34, 23, 15, 67, 89, 20, 54, 66
    };

    public static void main(String[] args) {
        heapSort(sItems);
    }

    /**
     * 标准插入排序
     */
    public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a) {
        int j;
        for (int i = 1; i < a.length; i++) {
            AnyType temp = a[i];
            for (j = i; j > 0 && temp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1]; // 将大于插入元素的其他所有元素逐个向后移动一位
            }
            a[j] = temp; // 将插入元素放入目标位置
        }
    }

    /**
     * 标准希尔排序 = 缩减增量的插入排序
     */
    public static <AnyType extends Comparable<? super AnyType>> void shellSort(AnyType[] a) {
        int j;
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                AnyType temp = a[i];
                for (j = i; j > gap && temp.compareTo(a[j - gap]) < 0; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = temp;
            }
        }
    }

    /**
     * 标准堆排序
     *
     * @param a an array of Comparable items
     */
    public static <AnyType extends Comparable<? super AnyType>> void heapSort(AnyType[] a) {
        /** buildHeap */
        for (int i = a.length / 2; i >= 0; i--) {
            percolateDown(a, i, a.length);
        }
        /** deleteMax */
        for (int i = a.length - 1; i > 0; i--) {
            swapReferences(a, 0, i);
            percolateDown(a, 0, i);
        }
        PrintUtil.println(Arrays.asList(a));
    }

    /**
     * Internal method of heap sort
     *
     * @param i the index of an item in the heap
     * @return the index of the left child
     */
    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * Internal method for heap sort that is used in deleteMax and buildHeap.
     *
     * @param a an array of Comparable items
     * @param i the position from which to percolate down
     * @param n the logical size of the heap
     */
    private static <AnyType extends Comparable<? super AnyType>> void percolateDown(AnyType[] a, int i, int n) {
        int child;
        AnyType temp;
        for (temp = a[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0)
                child++;
            if (temp.compareTo(a[child]) < 0)
                a[i] = a[child];
            else
                break;
        }
        a[i] = temp;
    }

    public static <AnyType extends Comparable<? super AnyType>> void swapReferences(AnyType[] a, int
            i1, int i2) {
        AnyType temp = a[i1];
        a[i1] = a[i2];
        a[i2] = temp;
    }

    /**
     * 标准归并排序(递归实现)
     */
    public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a) {
        AnyType[] temp = (AnyType[]) new Comparable[a.length];
        mergeSort(a, temp, 0, a.length - 1);
    }

    private static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a, AnyType[] temp, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, temp, left, center);
            mergeSort(a, temp, center + 1, right);
            merge(a, temp, left, center + 1, right);
        }
    }

    private static <AnyType extends Comparable<? super AnyType>> void merge(AnyType[] a, AnyType[] temp, int left, int right, int rightEnd) {
        int leftEnd = right - 1;
        int tempPosition = left;
        int numElements = rightEnd - left + 1;
        // Main loop
        while (left <= leftEnd && right <= rightEnd) {
            if (a[left].compareTo(a[right]) < 0) {
                temp[tempPosition++] = a[left++];
            } else {
                temp[tempPosition++] = a[right++];
            }
        }
        // left remains elements
        while (left <= leftEnd) {
            temp[tempPosition++] = a[left++];
        }
        // right remains elements
        while (right <= rightEnd) {
            temp[tempPosition++] = a[right++];
        }
        // Copy all elements back
        for (int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = temp[rightEnd];
        }
    }

    /**
     * 标准归并排序(非递归实现)
     * 原理：循环遍历每个大小下的两个子列进行合并，当前子列合并完毕后，就选择更大的那一层子列（当前子列大小的2倍）
     */
    public static <AnyType extends Comparable<? super AnyType>> void mergeSortNonRecursion(AnyType[] a, AnyType[] temp, int left, int right) {
        int total = a.length;
        for (int subListSize = 1; subListSize < total; subListSize *= 2) {
            int partOneStart = 0;
            while (partOneStart + subListSize < total) { // 只有当前子列后还有子列时才进行循环
                // 每两个子列进行合并
                int partTwoStart = partOneStart + subListSize + 1;
                int partTwoEnd = partTwoStart + subListSize;
                merge(a, temp, partOneStart, partTwoStart, partTwoEnd);
                // 跳过当前合并的两个子列进行下一轮的循环，合并接下来的两个新的子列
                partOneStart += partTwoEnd + 1;
            }
        }
    }

    public static final int CUTOFF = 11; // 值得使用快速排序的数组的起始大小

    /**
     * 标准快速排序
     **/
    public static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a) {
        quickSort(a, 0, a.length);
    }

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

    public static <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType[] a, int left,
                                                                           int right) {
        int center = (left + right) / 2;
        // 保证 left < center
        if (a[center].compareTo(a[left]) < 0) {
            swapReferences(a, left, center);
        }
        // 保证 left < right
        if (a[right].compareTo(a[left]) < 0) {
            swapReferences(a, left, right);
        }
        // 保证 center < right
        if (a[right].compareTo(a[center]) < 0) {
            swapReferences(a, center, right);
        }
        // 将枢纽元移动到倒数第二个位置
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    /**
     * 标准快速选择
     */
    public static <AnyType extends Comparable<? super AnyType>> void quickSelect(AnyType[] a, int k) {
        quickSelect(a, k, 0, a.length);
    }

    private static <AnyType extends Comparable<? super AnyType>> void quickSelect(AnyType[] a, int k, int left, int right) {
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
            if (k <= i) {
                // 排序小于枢纽元的元素
                quickSelect(a, k, left, i - 1);
            } else if (k > i + 1) {
                // 排序大于枢纽元的元素
                quickSelect(a, k, i + 1, right);
            }
        } else {
            // 使用插入排序
            insertionSort(a);
        }
    }
}
