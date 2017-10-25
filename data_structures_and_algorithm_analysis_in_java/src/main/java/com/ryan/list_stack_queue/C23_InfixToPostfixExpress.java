package com.ryan.list_stack_queue;

import com.ryan.util.PrintUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MUFCRyan on 2017/10/25.
 * 练习 3.23 中缀表达式转后缀表达式
 * a. 写出一个程序，将包含(,),+,-,*,/符号的中缀表达式转换成后缀表达式
 * b. 将取幂运算添加到指令系统中
 * c. 编写一个程序将后缀表达式转换为中缀表达式
 *
 * ab+c/d+e-
 * 1. (a+b)c/d+e-
 * 2. (a+b)/cd+e-
 * 3. (a+b)/c+de-
 * 4. (a+b)/c+d-e
 */

public class C23_InfixToPostfixExpress {

    private static Express sAddExpress;
    private static Express sSubExpress;
    private static Express sMultipleExpress;
    private static Express sDivideExpress;
    private static Express sLeftBracketExpress;
    private static Express sRightBracketExpress;
    private static Express sPowerExpress;

    private static final String INFIX_EXPRESS = "6 * ( 5 + ( 2 + 3 ) * 8 + 3 ^ 2 )";

    public static void main(String[] args){
        initExpress();
        PrintUtil.println(infixTpPostfix(INFIX_EXPRESS));
    }

    private static void initExpress() {
        sAddExpress = new Express(SYMBOL_ADD, 1);
        sSubExpress = new Express(SYMBOL_SUB, 1);
        sMultipleExpress = new Express(SYMBOL_MULTIPLE, 2);
        sDivideExpress = new Express(SYMBOL_DIVIDE, 2);
        sLeftBracketExpress = new Express(SYMBOL_LEFT_BRACKET, 3);
        sRightBracketExpress = new Express(SYMBOL_RIGHT_BRACKET, 3);
        sPowerExpress = new Express(SYMBOL_POWER, 4);
    }

    private static final String SYMBOL_ADD = "+";
    private static final String SYMBOL_SUB = "-";
    private static final String SYMBOL_MULTIPLE = "*";
    private static final String SYMBOL_DIVIDE = "/";
    private static final String SYMBOL_LEFT_BRACKET = "(";
    private static final String SYMBOL_RIGHT_BRACKET = ")";
    private static final String SYMBOL_POWER = "^";

    // TODO: 2017/10/25 先不做暂无法解决
    private static String infixTpPostfix(String express){
        Stack<Express> expressStack = new Stack<>();
        StringBuilder result = new StringBuilder();
        String[] expresses = express.trim().split(" ");
        for (int i = 0; i < expresses.length; i++) {
            String element = expresses[i];
            if (isNum(element)){
                result.append(element);
                result.append(" ");
            } else {
                Express realExpress = getExpress(element);
                if (expressStack.isEmpty()){
                    expressStack.push(realExpress);
                } else {
                    if (realExpress.isRightBracket()){
                        // 遇到 ) 不输出直接 pop 直到遇到 (，( 也不输出
                        Express temp = expressStack.peek();
                        while (!temp.isLeftBracket()){
                            temp = expressStack.pop();
                            if (!temp.isLeftBracket() && !temp.isRightBracket()){
                                result.append(temp.express);
                                result.append(" ");
                            }
                        }
                    } else if (realExpress.priority < expressStack.peek().priority){
                        // 当前运算符优先级小于栈顶运算符时
                        Express temp = expressStack.peek();
                        while (!expressStack.isEmpty() && temp.priority >= realExpress.priority && !expressStack.peek().isLeftBracket()){
                            // 循环 pop 栈顶运算符，知道栈顶运算符优先级小于当前运算符或栈顶运算符时 (
                            temp = expressStack.pop();
                            result.append(temp.express);
                            result.append(" ");
                        }
                        // 最后将当前运算符入栈
                        expressStack.push(realExpress);
                    } else {
                        // 一般情况下（当前运算符优先级大等于栈顶运算符）直接 push 入栈
                        expressStack.push(realExpress);
                    }
                }
            }
        }

        while (!expressStack.isEmpty()){
            // pop 出栈中所有运算符
            result.append(expressStack.pop().express);
        }

        return result.toString();
    }

    private static String postfixToInfix(String express){
        StringBuilder result = new StringBuilder();
        List<String> expressList = new ArrayList<>();
        String[] expresses = express.trim().split(" ");
        Stack<Express> expressStack = new Stack<>();
        for (int i = 0; i < expresses.length - 2; i++) {
            expressList.add(expresses[i]);
        }
        for (int i = 0; i < expressList.size() - 2; i++) {
            String realExpress = expressList.get(i);
            if (i < expresses.length - 2 && isNum(realExpress) && isNum(expressList.get(i + 1))){
                if (isNum(expresses[i + 2])){
                    String lastExpress = expressList.get(expresses.length - 1 - i);
                    if (!expressStack.isEmpty() && getExpress(lastExpress).priority < expressStack.peek().priority){
                        expressStack.pop();
                        result.append(sLeftBracketExpress);
                    }
                    result.append(realExpress);
                    result.append(lastExpress);
                    expressStack.push(getExpress(lastExpress));
                }
            }
        }
        return result.toString();
    }

    private static Express getExpress(String express){
        Express realExpress;
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
            case SYMBOL_POWER:
                realExpress = sPowerExpress;
                break;
            default :
                throw new RuntimeException("运算符 " + express + " 非法！请输入合法的运算符");
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
            case SYMBOL_POWER:
                result = (int) Math.pow(first, second);
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

        private boolean isPower(){
            return SYMBOL_POWER.equals(express);
        }
    }
}
