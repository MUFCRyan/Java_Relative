package com.ryan.list_stack_queue;

import java.util.Iterator;
import java.util.List;

/**
 * Created by MUFCRyan on 2017/10/23.
 * 练习 3.5 给定两个已排序的表 L1 和 L2，只使用基本的表操作编写计算 L1 ∪ L2（L1、L2的并集）
 */

public class C05_ListUnion {

    private <T extends Comparable<? super T>> List<T> union(List<T> list1, List<T> list2, List<T> union){
        if (list1 == null && list2 == null)
            return union;

        Iterator<T> iterator1 = list1.iterator();
        Iterator<T> iterator2 = list2.iterator();

        T item1 = iterator1.next();
        T item2 = iterator2.next();

        while (item1 != null || item2 != null){
            if (item1 == null){
                union.add(item2);
                item2 = iterator2.hasNext() ? iterator2.next() : null;
                continue;
            }

            if (item2 == null){
                union.add(item1);
                item1 = iterator1.hasNext() ? iterator1.next() : null;
                continue;
            }

            int compareResult = item1.compareTo(item2);
            if (compareResult == 0){
                union.add(item1);
                item1 = iterator1.hasNext() ? iterator1.next() : null;
                item2 = iterator2.hasNext() ? iterator2.next() : null;
            } else if (compareResult < 0){
                union.add(item1);
                item1 = iterator1.hasNext() ? iterator1.next() : null;
            } else {
                union.add(item2);
                item2 = iterator2.hasNext() ? iterator2.next() : null;
            }
        }

        return union;
    }
}
