package com.ryan.list_stack_queue;

import com.ryan.util.PrintUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

/**
 * Created by MUFCRyan on 2017/10/24.
 * 练习 3.21 编写检测平衡符号的程序，Java()
 */

public class C21_BalanceSign {
    private static final String FILE_SUFFIX = ".java";
    private static final String FIRST_FILE_PATH =
            "D:\\Sources\\OtherFrame\\Java_Relative" +
                    "\\data_structures_and_algorithm_analysis_in_java\\src\\main\\java\\com\\ryan\\list_stack_queue\\C20_LazyDeletion" + FILE_SUFFIX;

    public static void main(String[] args){
        PrintUtil.println(isBalance(new File(FIRST_FILE_PATH)));
    }

    private static final char SLASH = '/';
    private static final char ASTERISK = '*';
    private static final char START_SMALL_BRACKET = '(';
    private static final char END_SMALL_BRACKET = ')';
    private static final char START_MIDDLE_BRACKET = '[';
    private static final char END_MIDDLE_BRACKET = ']';
    private static final char START_BIG_BRACKET = '{';
    private static final char END_BIG_BRACKET = '}';

    private static boolean isBalance(File file){
        if (file == null || !file.exists())
            return false;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            Stack<Character> stack = new Stack<>();
            while ((line = reader.readLine()) != null){
                if (line.isEmpty() || !containsSign(line))
                    continue;
                line = line.trim();
                char[] chars = line.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char sign = chars[i];
                    switch(sign){
                        case SLASH:
                            if (i < chars.length - 1){
                                if (ASTERISK == chars[i+1]){
                                    stack.push(SLASH);
                                    stack.push(ASTERISK);
                                    i++;

                                    PrintUtil.print(sign);
                                    PrintUtil.print(ASTERISK);
                                }
                            }
                            break;
                        case ASTERISK:
                            if (stack.size() < 2)
                                return false;
                            if (i < chars.length - 1){
                                if (SLASH == chars[i+1]){

                                    Character asterisk = stack.peek();
                                    if (!asterisk.equals(ASTERISK))
                                        return false;
                                    else {
                                        asterisk = stack.pop();
                                    }

                                    Character slash = stack.peek();
                                    if (!slash.equals(SLASH)){
                                        stack.push(asterisk);
                                        return false;
                                    } else {
                                        stack.pop();
                                    }

                                    PrintUtil.print(sign);
                                    PrintUtil.println(SLASH);

                                    i++;
                                }
                            }
                            break;
                        case START_SMALL_BRACKET:
                        case START_MIDDLE_BRACKET:
                        case START_BIG_BRACKET:
                            stack.push(sign);
                            PrintUtil.print(sign);
                            break;
                        case END_SMALL_BRACKET:
                            if (stack.isEmpty())
                                return false;
                            if (!stack.peek().equals(START_SMALL_BRACKET)){
                                return false;
                            }
                            stack.pop();
                            PrintUtil.println(sign);
                            break;
                        case END_MIDDLE_BRACKET:
                            if (stack.isEmpty())
                                return false;
                            if (!stack.peek().equals(START_MIDDLE_BRACKET)){
                                return false;
                            }
                            stack.pop();
                            PrintUtil.println(sign);
                            break;
                        case END_BIG_BRACKET:
                            if (stack.isEmpty())
                                return false;
                            if (!stack.peek().equals(START_BIG_BRACKET)){
                                return false;
                            }
                            stack.pop();
                            PrintUtil.println(sign);
                            break;
                    }
                }
            }
            if (!stack.isEmpty())
                return false;
            return true;
        } catch (Exception e) {
            PrintUtil.print(e.getMessage());
            return false;
        } finally {
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    private static boolean containsSign(String sign){
        if (sign.indexOf(SLASH) > -1)
            return true;
        if (sign.indexOf(ASTERISK) > -1)
            return true;
        if (sign.indexOf(START_SMALL_BRACKET) > -1)
            return true;
        if (sign.indexOf(START_MIDDLE_BRACKET) > -1)
            return true;
        if (sign.indexOf(START_BIG_BRACKET) > -1)
            return true;
        if (sign.indexOf(END_SMALL_BRACKET) > -1)
            return true;
        if (sign.indexOf(END_MIDDLE_BRACKET) > -1)
            return true;
        return sign.indexOf(END_BIG_BRACKET) > -1;
    }
}
