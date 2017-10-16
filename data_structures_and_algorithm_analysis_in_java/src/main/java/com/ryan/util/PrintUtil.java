package com.ryan.util;

/**
 * Created by MUFCRyan on 2017/10/16.
 * 打印工具类
 */

public class PrintUtil {
    public static void println(){
        System.out.println();
    }

    public static void println(Object object){
        System.out.println(object.toString());
    }

    public static void print(Object object){
        System.out.print(object.toString());
    }
}
