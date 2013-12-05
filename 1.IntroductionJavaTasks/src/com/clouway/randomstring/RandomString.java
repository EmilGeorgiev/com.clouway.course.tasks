package com.clouway.randomstring;

import java.util.Random;
import java.lang.StringBuilder;

public class RandomString {

    static final int MIN = 65;
    static final int MAX = 122;

    /**
     * Generates a random string of any length.Use class Random.
     *
     * @param maxLengh  maximum word length
     * @param minLenght minimal word length
     * @return random word
     */
    public String generateRandomString(int maxLengh, int minLenght) {
        Random random = new Random();
        //calculate the length of the String
        int lenghtOfString = random.nextInt((maxLengh - minLenght) + 1) + minLenght;
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i <= lenghtOfString - 1; i++) {
            int randomNum = random.nextInt((MAX - MIN) + 1) + MIN;
            char symbol = (char) randomNum;
            result.append(symbol);
        }
        return result.toString();
    }
}
