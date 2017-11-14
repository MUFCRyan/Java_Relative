package com.ryan.heap;

/**
 * Created by zhaofengchun on 2017/11/13.
 * 练习 6.12 编写一个程序输入 N 个元素并
 *      a. 将它们逐个插入堆中
 *      b. 以线性时间建立一个堆
 *      比较这两个算法对于已排序、反序以及随机输入的运行时间
 */

public class F12_BuildHeap {
    private static final int MAX_SIZE = 1000;

    private void buildHeapVerbatim(Integer[] elements){
        Integer[] heap = new Integer[MAX_SIZE];
        for (Integer element : elements) {
            insertVerbatim(heap, element);
        }
    }

    private boolean insertVerbatim(Integer[] heap, Integer element){
        // 1. 找到第一个空位
        if (heap == null || heap.length == 0)
            return false;
        int position = 1;
        while (heap[position] != null)
            position ++;

        // 2. 和空位的父节点的值进行比较，如果 >= 父节点的值或者父节点不存在就停止，否则与父节点交换继续进行比较，父节点所在的位置 = 子节点位置 / 2
        while (position > 0){
            int compareResult = element.compareTo(heap[position / 2]);
            if (compareResult >= 0){
                heap[position] = heap[position / 2]; // 父节点下移
                position /= 2; // 子节点上滤
            } else {
                heap[position] = element;
                break;
            }
        }
        return true;
    }

    private void buildHeapOnce(Integer[] elements){
        Integer[] heap = new Integer[MAX_SIZE];
        int heapSize = 0;
        for (int i = 0; i < elements.length; i++) {
            heap[i + 1] = elements[i];
            heapSize ++;
        }
        for (int size = heapSize / 2; size > 0; size--) {
            // 左儿子位置 2i，右儿子位置 2i + 1
            Integer min = heap[size * 2];
            if (size * 2 + 1 <= heapSize - 1){
                min = Math.min(heap[size * 2], heap[size * 2 + 1]);
            }
            int compareResult = min.compareTo(heap[size]);
            if (compareResult < 0){
                if (heap[size * 2].compareTo(heap[size * 2 + 1]) < 0){
                    heap[size * 2] = heap[size];
                } else {
                    heap[size * 2 + 1] = heap[size];
                }
                heap[size] = min;
            }
        }
    }
}
