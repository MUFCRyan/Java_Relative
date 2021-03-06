package com.ryan.introduction;

import com.ryan.util.PrintUtil;

/**
 * Created by MUFCRyan on 2017/10/16.
 * 06 全排列
 */

public class A06_FullArray {
    public static void main(String[] args){
        String string = "abcdef";
        A06_FullArray fullArray = new A06_FullArray();
        fullArray.permute(string);
    }

    private void permute(String string){
        char[] chars = string.toCharArray();
        permute(chars, 0, chars.length - 1);
    }

    private void permute(char[] chars, int low, int high){
        if (low >= high){
            // 找到最后一个字母后将当前排列的所有字母全部打印出来
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                builder.append(chars[i]);
            }
            PrintUtil.print(builder.toString());
            PrintUtil.println();
            return;
        }

        // 遍历找出每种可能的排列组合，原理：每次固定一个字符，然后在剩余的列表中再次进行列表的循环，在每次循环中继续调用该函数进行递归，直到遍历到最后一个字母代表找到当前的排列；
        // 然后到上一层继续执行未完成的循环和递归
        for (int i = low; i <= high; i++) { // high 代表的是最后一个元素的下标而非数组的大小，必须遍历到
            swamp(chars, i, high);
            permute(chars, low + 1, high);
            swamp(chars, i, high);
        }
    }

    private void swamp(char[] chars, int i, int high) {
        char temp = chars[i];
        chars[i] = chars[high];
        chars[high] = temp;
    }
}
