package com.ryan.list_stack_queue;

import java.util.Stack;

/**
 * Created by MUFCRyan on 2017/10/24.
 * 练习 3.22 编写一个程序计算后缀表达式的值
 */

public class C22_PostFixExpress {
    public static void main(String[] args){

    }

    private static final char SYMBOL_ADD = '+';
    private static final char SYMBOL_SUB = '-';
    private static final char SYMBOL_MULTIPLE = '*';
    private static final char SYMBOL_DIVIDE = '/';
    private static final char SYMBOL_LEFT_BRACKET = '(';
    private static final char SYMBOL_RIGHT_BRACKET = ')';

    private static long evaluatePostFix(String express){
        long value = 0;
        Stack<Character> expressStack = new Stack<>();
        Stack<Character> numStack = new Stack<>();

        char[] chars = express.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char element = chars[i];
            if (isNum(element)){
                numStack.push(element);
            } else {
                expressStack.push(element);
            }
        }

        return value;
    }

    private static boolean isNum(char num){
        return '0' <= num && num <= '9';
    }

    private static class Express implements Comparable<Express>{
        private int priority = 0;

        @Override
        public int compareTo(Express express) {
            if (priority == express.priority)
                return 0;
            if (priority < express.priority)
                return -1;
            if (priority > express.priority)
                return 1;
            return 0;
        }
    }
}
