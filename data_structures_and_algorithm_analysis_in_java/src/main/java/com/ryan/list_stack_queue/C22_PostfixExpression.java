package com.ryan.list_stack_queue;

import com.ryan.util.PrintUtil;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MUFCRyan on 2017/10/24.
 * 练习 3.22 编写一个程序计算后缀表达式的值
 */

public class C22_PostfixExpression {

    private static Express sAddExpress;
    private static Express sSubExpress;
    private static Express sMultipleExpress;
    private static Express sDivideExpress;
    private static Express sLeftBracketExpress;
    private static Express sRightBracketExpress;

    private static final String POSTFIX_EXPRESS = "6 5 2 3 + 8 * + 3 + *";

    public static void main(String[] args){
        initExpress();
        PrintUtil.println(evaluatePostfix(POSTFIX_EXPRESS));
    }

    private static void initExpress() {
        sAddExpress = new Express(SYMBOL_ADD, 1);
        sSubExpress = new Express(SYMBOL_SUB, 1);
        sMultipleExpress = new Express(SYMBOL_MULTIPLE, 2);
        sDivideExpress = new Express(SYMBOL_DIVIDE, 2);
        sLeftBracketExpress = new Express(SYMBOL_LEFT_BRACKET, 3);
        sRightBracketExpress = new Express(SYMBOL_RIGHT_BRACKET, 3);
    }

    private static final String SYMBOL_ADD = "+";
    private static final String SYMBOL_SUB = "-";
    private static final String SYMBOL_MULTIPLE = "*";
    private static final String SYMBOL_DIVIDE = "/";
    private static final String SYMBOL_LEFT_BRACKET = "(";
    private static final String SYMBOL_RIGHT_BRACKET = ")";

    private static int evaluatePostfix(String express){
        int value = 0;
        Stack<Integer> numStack = new Stack<>();
        String[] chars = express.trim().split(" ");
        for (int i = 0; i < chars.length; i++) {
            String element = chars[i];
            if (isNum(element)){
                int number = Integer.parseInt(element);
                numStack.push(number);
            } else if (!numStack.isEmpty()){
                Express realExpress = getExpress(element);
                Integer second = numStack.pop();
                Integer first = numStack.pop();
                value = getValue(realExpress, first, second);
                numStack.push(value);
            }
        }

        return value;
    }

    private static Express getExpress(String express){
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

    private static boolean isNum(String num){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(num);
        return matcher.matches();
    }

    private static class Express implements Comparable<Express>{
        private int priority = 0;
        private String express;

        Express(String express, int priority){
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
            return SYMBOL_ADD.equals(express);
        }

        private boolean isSub(){
            return SYMBOL_SUB.equals(express);
        }

        private boolean isMultiple(){
            return SYMBOL_MULTIPLE.equals(express);
        }

        private boolean isDivide(){
            return SYMBOL_DIVIDE.equals(express);
        }

        private boolean isLeftBracket(){
            return SYMBOL_LEFT_BRACKET.equals(express);
        }

        private boolean isRightBracket(){
            return SYMBOL_RIGHT_BRACKET.equals(express);
        }
    }
}
