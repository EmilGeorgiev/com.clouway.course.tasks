package com.clouway.commondivisor;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCalculator {

    @Test
    public void testGCD() {
        Calculator calc = new Calculator(12, 3);
        int greatestCommonDivisor = calc.findGCDPartition();
        assertEquals(3, greatestCommonDivisor);
    }

    @Test
    public void testGCDTwoNumbersIsEquals() {
        Calculator calc = new Calculator(12, 12);
        int greatestCommonDivisor = calc.findGCDPartition();
        assertEquals(12, greatestCommonDivisor);
    }

    @Test
    public void testGCDOneOfTheTwoNumbersIsOne() {
        Calculator calc = new Calculator(1, 12);
        int greatestCommonDivisor = calc.findGCDPartition();
        assertEquals(1, greatestCommonDivisor);
    }

    @Test
    public void testLCMOfTwoPrimeNUmbers() {
        Calculator calc = new Calculator(7, 5);
        int greatestCommonDivisor = calc.findLCMCommonDivisor();
        assertEquals(35, greatestCommonDivisor);
    }

    @Test
    public void testLCMOfTwoEvenNumbers() {
        Calculator calc = new Calculator(2, 0);
        int greatestCommonDivisor = calc.findLCMCommonDivisor();
        assertEquals(0, greatestCommonDivisor);
    }

    @Test
    public void testLCMOfTwoNegativeNumbers() {
        Calculator calc = new Calculator(7, -9);
        int greatestCommonDivisor = calc.findLCMSimpleMultipliers();
        assertEquals(-63, greatestCommonDivisor);
    }


}
