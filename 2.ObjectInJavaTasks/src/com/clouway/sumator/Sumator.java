package com.clouway.sumator;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Sumator implements Sum {
    /**
     * Sum two numbers of type Integer.
     */
    @Override
    public int sum(int a, int b) {
        int result = a + b;
        return result;
    }

    /**
     * Sum two numbers of type double.
     */
    @Override
    public double sum(double a, double b) {
        double result = a + b;
        return result;
    }

    /**
     * Parse two strings if they are composed only of numbers returns their sum.Otherwise throws exception.
     */
    @Override
    public int sum(String a, String b)throws NumberFormatException {
        if (a == null && b == null) {
            throw new NullPointerException("");

        }
        int result = 0;

        int first = Integer.parseInt(a);
        int second = Integer.parseInt(b);
        result = first + second;


        return result;
    }

    /**
     * Sum two numbers of type BygIteger.
     */
    @Override
    public BigInteger sum(BigInteger a, BigInteger b) {
        BigInteger result = a.add(b);
        return result;
    }

    /**
     * Sum two numbers of type BygDecimal.
     */
    @Override
    public BigDecimal sum(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.add(b);
        return result;
    }

}
