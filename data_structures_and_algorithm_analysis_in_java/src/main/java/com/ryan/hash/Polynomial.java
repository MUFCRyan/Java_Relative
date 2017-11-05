package com.ryan.hash;

/**
 * Created by zhaofengchun on 2017/11/5.
 * 多项式对象，由常数和幂次构成
 */

public class Polynomial {
    public int constant;
    public int power;
    public boolean isHead = false;
    public Polynomial next;

    public Polynomial(int constant, int power, Polynomial next) {
        this.constant = constant;
        this.power = power;
        this.next = next;
    }

    public Polynomial(int constant, int power, boolean isHead, Polynomial next) {
        this.constant = constant;
        this.power = power;
        this.isHead = isHead;
        this.next = next;
    }
}
