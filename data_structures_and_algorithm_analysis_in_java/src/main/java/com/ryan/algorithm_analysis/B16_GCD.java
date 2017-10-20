package com.ryan.algorithm_analysis;

import com.ryan.util.PrintUtil;

/**
 * Created by MUFCRyan on 2017/10/20.
 * 练习：2.16，基于下列各式编写另外的 gcd 算法（a > b）
 * 1. gcd(a, b) = 2gcd(a/2, b/2);  a、b 均为偶数
 * 2. gcd(a, b) = gcd(a/2, b);     a 为偶数
 * 3. gcd(a, b) = gcd(a, b/2);     b 为偶数
 * 4. gcd(a, b) = gcd((a+b)/2, (a-b)/2);   a、b 均为奇数
 */

public class B16_GCD {
    public static void main(String[] args) {
        PrintUtil.println(gcd(100, 50));
    }

    private static long gcd(long a, long b) {
        // gcd(a, b) = 2gcd(a/2, b/2);  a、b 均为偶数
        if (a % 2 == 0 && b % 2 == 0 && (a != 0 && b != 0)) {
            return 2 * gcd(a / 2, b / 2);
        }

        // gcd(a, b) = gcd(a/2, b); a 为偶数
        if (a % 2 == 0 && a != 0)
            return gcd(a / 2, b);
        // gcd(a, b) = gcd(a, b/2); b 为偶数
        if (b % 2 == 0 && b != 0)
            return gcd(a, b / 2);

        // gcd(a, b) = gcd((a+b)/2, (a-b)/2);   a、b 均为奇数
        if (a != 0 && b != 0){
            return gcd((a + b) / 2, (a - b) / 2);
        }
        return a;
    }
}
