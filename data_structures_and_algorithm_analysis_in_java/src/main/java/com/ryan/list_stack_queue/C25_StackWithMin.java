package com.ryan.list_stack_queue;

import java.util.Stack;

/**
 * Created by zhaofengchun on 2017/10/25.
 * 练习 3.25
 * a. 提出一种数据结构支持栈 push 和 pop 操作以及第三种操作 findMin, 它返回该数据结构中的最小元素。所有操作均以 O(1) 最坏情形时间运行
 * b. 证明，如果我们添加找出并删除最小元素的第4种操作 deleteMin，那么至少有一种操作必然花费 Ω(logN) 时间（需阅读第七章）
 */

public class C25_StackWithMin<T extends Comparable<T>>{
    private Stack<T> operation = new Stack();
    private Stack<T> minElement = new Stack();

    /** a */
    private boolean push(T element){
        operation.push(element);
        if (minElement.isEmpty())
            minElement.push(element);
        else if (minElement.peek().compareTo(element) >= 0){
            minElement.push(element);
        }
        return true;
    }

    private boolean pop(){
        if (operation.isEmpty())
            return false;
        T pop = operation.pop();
        if (!minElement.isEmpty() && pop.compareTo(minElement.peek()) == 0){
            minElement.pop();
        }
        return true;
    }

    private T findMin(){
        if (minElement.isEmpty())
            return null;
        return minElement.pop();
    }

    // TODO: 2017/10/25 等学完第七章再解决 b
}
