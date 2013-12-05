package com.clouway.commondivisor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 11/25/13
 * Time: 4:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Calculator implements InventorDivisorAndMUltiple {

    private int firstNum;
    private int secondNum;
    private int greatestCommonDivisor;

    /**
     * Save prime divisors og the dividend
     */
    private List<Integer> primeDivisorsOfDividend;

    /**
     * Save prime divisors og the divisor
     */
    private List<Integer> primeDivisorsOfDivisor;

    /**
     * Constructor of the Calculator.
     *
     * @param firstNumber  one of the numbers, which find greatest common divisor and the least common multiple.
     * @param secondNumber one of the numbers, which find greatest common divisor and the least common multiple.
     */
    public Calculator(int firstNumber, int secondNumber) {
        if (firstNumber > secondNumber) {
            this.firstNum = firstNumber;
            this.secondNum = secondNumber;
        } else if (firstNumber < secondNumber) {
            this.firstNum = secondNumber;
            this.secondNum = firstNumber;
        } else {
            this.firstNum = firstNumber;
            this.secondNum = secondNumber;
        }
        this.greatestCommonDivisor = 0;
        this.primeDivisorsOfDividend = new ArrayList<Integer>();
        this.primeDivisorsOfDivisor = new ArrayList<Integer>();
    }

    /**
     * Finds the lowest common divisor of two integers.Use the algorithm of Euclid's subtraction.
     *
     * @return lowest common divisor
     */
    @Override
    public int findGCDSubtraction() {
        if (firstNum == 0 && secondNum == 0) {
            return 0;
        }
        if (firstNum == secondNum) {
            return firstNum;
        }
        if (firstNum > secondNum) {
            firstNum = firstNum - secondNum;
        } else {
            secondNum = secondNum - firstNum;
        }
        return findGCDSubtraction();

    }

    /**
     * Find lowest common multiple by the greatest common divisor.
     *
     * @return lowest common multiple
     */
    @Override
    public int findLCMCommonDivisor() {
        int dividend = (firstNum * secondNum);
        int devisor = findGCDSubtraction();
        int result = dividend / devisor;
        return result;
    }

    /**
     * Finds the greatest common divisor of two integers.Use Euclid's algorithm
     * to partition.
     *
     * @return the greatest common divisors
     */
    @Override
    public int findGCDPartition() {

        if (this.firstNum == this.secondNum) {
            return firstNum;
        }
        while (secondNum != 0) {
            greatestCommonDivisor = secondNum;
            secondNum = (firstNum % secondNum);
            firstNum = greatestCommonDivisor;
        }
        return greatestCommonDivisor;
    }

    /**
     * Finds lowest common multiple of two integers.Using fragmentation of
     * simple multipliers
     *
     * @return lowest common multiple
     */
    @Override
    public int findLCMSimpleMultipliers() {
        addPrimeDivisorsInLists(firstNum, secondNum);
        List<Integer> commonPrimeDivisors = new ArrayList<Integer>();
        for (Integer integer : primeDivisorsOfDividend) {
            if (primeDivisorsOfDivisor.contains(integer)) {
                commonPrimeDivisors.add(integer);
                primeDivisorsOfDivisor.remove(integer);
            } else {
                commonPrimeDivisors.add(integer);
            }
        }

        commonPrimeDivisors.addAll(primeDivisorsOfDivisor);

        int result = 1;
        for (Integer integer : commonPrimeDivisors) {
            result *= integer;
        }
        System.out.println("Lowest Common Multiple is: " + result);
        return result;
    }

    /**
     * Finds the smallest divisor of a number greater than one.
     *
     * @param number is the number of which we find the smallest prime divisor.
     * @return the smallest prime divisor.
     */
    private int findLeastDivisorDifferentFromOne(int number) {
        if (number != 1 && number != 0) {
            for (int i = 2; i < number; i++) {
                int result = (number % i);
                if (result == 0) {
                    return (i);
                }
            }
            return number;
        } else {
            return 1;
        }
    }

    /**
     * Adds all prime divisors of two numbers in two lists.
     *
     * @param firstNumber  is first element
     * @param secondNumber is second element
     */
    private void addPrimeDivisorsInLists(int firstNumber, int secondNumber) {
        if ((firstNumber == 1) && secondNumber == 1) {
            return;
        }

        int primeDevisor = findLeastDivisorDifferentFromOne(firstNumber);
        primeDivisorsOfDividend.add(primeDevisor);
        int aFirstNumber = (firstNumber / primeDevisor);
        primeDevisor = findLeastDivisorDifferentFromOne(secondNumber);
        primeDivisorsOfDivisor.add(primeDevisor);
        int aSecondNumber = (secondNumber / primeDevisor);
        addPrimeDivisorsInLists(aFirstNumber, aSecondNumber);
    }
}
