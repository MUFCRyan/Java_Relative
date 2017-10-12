package com.ryan.introduction;

/**
 * Created by MUFCRyan on 2017/10/12.
 * 打印任意 double 型变量，此处解决方案不完善，只是利用了 for 循环逐个字符打印
 */

public class PrintDouble {

    public static void main(String[] args){
        PrintDouble printDouble = new PrintDouble();
        printDouble.printOut(12345);
        System.out.println();
        printDouble.printDouble(-123456.123456);
    }
    
    void printDouble(double number){
        String string = String.valueOf(number);
        char[] chars = string.toCharArray();
        for (char charNumber : chars) {
            printDigit(String.valueOf(charNumber));
        }
    }

    void printOut(int n){
        if (n > 10)
            printOut(n / 10);
        printDigit(String.valueOf(n % 10));
    }

    private void printDigit(String n) {
        System.out.print(n);
    }
}
