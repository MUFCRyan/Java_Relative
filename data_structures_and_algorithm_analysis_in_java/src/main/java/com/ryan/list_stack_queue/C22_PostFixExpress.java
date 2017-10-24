package com.ryan.list_stack_queue;

import com.ryan.util.PrintUtil;

import java.util.Stack;

/**
 * Created by MUFCRyan on 2017/10/24.
 * 练习 3.22 编写一个程序计算后缀表达式的值
 */

public class C22_PostFixExpress {

    private static Express sAddExpress;
    private static Express sSubExpress;
    private static Express sMultipleExpress;
    private static Express sDivideExpress;
    private static Express sLeftBracketExpress;
    private static Express sRightBracketExpress;

    private static final String POSTFIX_EXPRESS = "6523+8*+3+*";

    public static void main(String[] args){
        initExpress();
        PrintUtil.println(evaluatePostFix(POSTFIX_EXPRESS));
    }

    private static void initExpress() {
        sAddExpress = new Express(SYMBOL_ADD, 1);
        sSubExpress = new Express(SYMBOL_SUB, 1);
        sMultipleExpress = new Express(SYMBOL_MULTIPLE, 2);
        sDivideExpress = new Express(SYMBOL_DIVIDE, 2);
        sLeftBracketExpress = new Express(SYMBOL_LEFT_BRACKET, 3);
        sRightBracketExpress = new Express(SYMBOL_RIGHT_BRACKET, 3);
    }

    private static final char SYMBOL_ADD = '+';
    private static final char SYMBOL_SUB = '-';
    private static final char SYMBOL_MULTIPLE = '*';
    private static final char SYMBOL_DIVIDE = '/';
    private static final char SYMBOL_LEFT_BRACKET = '(';
    private static final char SYMBOL_RIGHT_BRACKET = ')';

    private static int evaluatePostFix(String express){
        int value = 0;
        Stack<Integer> numStack = new Stack<>();
        char[] chars = express.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char element = chars[i];
            if (isNum(element)){
                int number = Integer.parseInt(String.valueOf(element));
                numStack.push(number);
            } else if (numStack.size() > 0){
                Express realExpress = getExpress(element);
                Integer second = numStack.pop();
                Integer first = numStack.pop();
                value = getValue(realExpress, first, second);
                numStack.push(value);
            }
        }

        return value;
    }

    private static Express getExpress(char express){
        Express realExpress = null;
        switch(express){
            case SYMBOL_ADD:
                realExpress = sAddExpress;
                break;
            case SYMBOL_SUB:
                realExpress = sSubExpress;
                break;
            case SYMBOL_MULTIPLE:
                realExpress = sMultipleExpress;
                break;
            case SYMBOL_DIVIDE:
                realExpress = sDivideExpress;
                break;
            case SYMBOL_LEFT_BRACKET:
                realExpress = sLeftBracketExpress;
                break;
            case SYMBOL_RIGHT_BRACKET:
                realExpress = sRightBracketExpress;
                break;
            default :
                break;
        }
        return realExpress;
    }
    
    private static int getValue(Express express, Integer num1, Integer num2){
        int first = num1.intValue();
        int second = num2.intValue();
        int result = 0;
        switch(express.express){
            case SYMBOL_ADD:
                result = first + second;
                break;
            case SYMBOL_SUB:
                result = first - second;
                break;
            case SYMBOL_MULTIPLE:
                result = first * second;
                break;
            case SYMBOL_DIVIDE:
                result = first / second;
                break;
        }
        return result;
    }

    private static boolean isNum(char num){
        return '0' <= num && num <= '9';
    }

    private static class Express implements Comparable<Express>{
        private int priority = 0;
        private char express;

        Express(char express, int priority){
            this.express = express;
            this.priority = priority;
        }

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

        private boolean isAdd(){
            return SYMBOL_ADD == express;
        }

        private boolean isSub(){
            return SYMBOL_SUB == express;
        }

        private boolean isMultiple(){
            return SYMBOL_MULTIPLE == express;
        }

        private boolean isDivide(){
            return SYMBOL_DIVIDE == express;
        }

        private boolean isLeftBracket(){
            return SYMBOL_LEFT_BRACKET == express;
        }

        private boolean isRightBracket(){
            return SYMBOL_RIGHT_BRACKET == express;
        }
    }
}
