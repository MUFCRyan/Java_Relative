package com.ryan.hash;

/**
 * Created by zhaofengchun on 2017/11/5.
 * 练习 5.11 编写一个程序，实现下面的方案，将大小分别为 M 和 N 的两个稀疏多项式 P1、P2 相乘。每个多项式表示成为对象的一个链表，这些对象由系数和幂组成。我们用 P2 的项乘以
 * P1 的每一项，总数为 MN 次运算。一种方法是将这些项排序合并同类项，但是，这需要排序 MN个记录，代价可能很高，特别是在小内存环境下。另一种方案，我们可以在多项式的项进
 * 行计算时将它们合并，然后将结果排序。
 * a. 编写一个程序实现第二种方案
 * b. 如果输出多项式大约有 O(M+N) 项，两种方法的运行时间各是多少
 */

public class E11_CalculatePolynomial {
    private static final int[] p1Params = new int[]{
            3, 4,
            5, 6,
            -2, 7,
            1, 2,
            -8, 1,
            6, 3
    };

    private static final int[] p2Params = new int[]{
            3, 4,
            4, 5,
            2, 7,
            -1, 2,
            8, 1,
            6, 3
    };

    private static Polynomial p1 = new Polynomial(0, 0, true, null);
    private static Polynomial p2 = new Polynomial(0, 0, true, null);

    public static void main(String[] args) {
        p1 = initPolynomial(p1, p1Params);
        p2 = initPolynomial(p2, p2Params);
        Polynomial result = new Polynomial(0, 0, true, null);
        Polynomial calculate = calculate(p1, p2, result);
    }

    private static Polynomial initPolynomial(Polynomial p, int[] params) {
        Polynomial p1Head = p;
        for (int i = 0; i < params.length; i += 2) {
            p.next = new Polynomial(params[i], params[i + 1], null);
            p = p.next;
        }
        p = p1Head;
        return p;
    }

    private static Polynomial calculate(Polynomial p1, Polynomial p2, Polynomial result) {
        Polynomial resultHead = result;
        Polynomial p1Head = p1;
        Polynomial p2Head = p2;
        p1 = p1.next;
        p2 = p2.next;
        while (p2Head != null) {
            while (p1 != null) {
                // 将计算结果存入 resultHead 中，同时对其进行排序
                int constant = p2.constant * p1.constant;
                if (constant == 0) {
                    p1 = p1.next;
                    continue;
                }
                int power = p2.power + p1.power;
                while (resultHead.next != null && resultHead.next.power < power) {
                    // 可以将 resultHead 的当前项移动到空或者与结果次幂相等项或者第一个比结果次幂大的项
                    resultHead = resultHead.next;
                }
                if (resultHead.next == null) {
                    // 该项为 null，直接插入
                    resultHead.next = new Polynomial(constant, power, null);
                } else if (resultHead.next.power == power) {
                    // 同次幂项合并
                    if (resultHead.next.constant + constant == 0) {
                        // 合并后当前项的常数项为0时将其删除
                        if (resultHead.next.next != null) {
                            // 有下一项就将当前项的引用指向下一项
                            resultHead.next = resultHead.next.next;
                        } else {
                            // 另当前项为 null
                            resultHead.next = null;
                        }
                    } else {
                        resultHead.next.constant += constant;
                    }
                } else if (resultHead.next.power > power) {
                    // 将当前项向后移动并将计算结果项添加到当前位置上
                    Polynomial polynomial = new Polynomial(resultHead.next.constant, resultHead.next.power, resultHead.next.next);
                    resultHead.next = polynomial;
                    resultHead.next.constant = constant;
                    resultHead.next.power = power;
                }
                p1 = p1.next;
                resultHead = result.next;
            }
            p1 = p1Head.next;
            p2Head = p2Head.next;
        }
        p1 = p1Head;
        p2 = p2Head;
        return result;
    }
}
