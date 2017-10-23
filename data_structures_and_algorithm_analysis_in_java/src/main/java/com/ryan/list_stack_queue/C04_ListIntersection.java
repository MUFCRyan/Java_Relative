package com.ryan.list_stack_queue;

import java.util.Iterator;
import java.util.List;

/**
 * Created by MUFCRyan on 2017/10/23.
 * 练习 3.4 给定两个已排序的表 L1 和 L2，只使用基本的表操作编写计算 L1 ∩ L2（L1、L2的交集）
 */

public class C04_ListIntersection {

    private <T extends Comparable<? super T>> List<T> intersection(List<T> list1, List<T> list2, List<T> intersection){
        if (list1 == null || list2 == null)
            return intersection;

        Iterator<T> iterator1 = list1.iterator();
        Iterator<T> iterator2 = list2.iterator();

        T item1 = iterator1.next();
        T item2 = iterator2.next();

        if (item1.equals(item2)){
            intersection.add(item1);
        }

        while (item1 != null && item2 != null){
            int compareResult = item1.compareTo(item2);
            if (compareResult == 0){
                intersection.add(item1);
                item1 = iterator1.hasNext() ? iterator1.next() : null;
                item2 = iterator2.hasNext() ? iterator2.next() : null;
            } else if (compareResult < 0){ // 谁小谁往后移动
                item1 = iterator1.hasNext() ? iterator1.next() : null;
            } else {
                item2 = iterator2.hasNext() ? iterator2.next() : null;
            }
        }
        return intersection;
    }
}
