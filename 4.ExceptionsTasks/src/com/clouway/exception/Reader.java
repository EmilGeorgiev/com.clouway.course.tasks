package com.clouway.exception;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 11/26/13
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Reader {

    /**
     * Min value of the range.
     */
    private final int min;
    /**
     * Max value of the range.
     */
    private final int max;
    private Scanner scan;

    public Reader(int min, int max) {
        this.min = min;
        this.max = max;
        scan = new Scanner(System.in);
    }

    /**
     * Read numbers of the console, while number is large ot the zero and less of the hundred.
     *
     * @throws OutOfRangeException
     */
    public void readOfConsole() throws OutOfRangeException {
        while (true) {
            int index;
            index = scan.nextInt();
            if (min > index || index > max) {
                scan.close();
                throw new OutOfRangeException(min, max);
            }

        }

    }
}
