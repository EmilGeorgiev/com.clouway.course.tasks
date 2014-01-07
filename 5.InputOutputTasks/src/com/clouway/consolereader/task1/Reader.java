package com.clouway.consolereader.task1;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/10/13
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Reader {

    private Scanner scanner;
    private String message;

    public Reader(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    /**
     * Read String of console.
     * @return String.
     */
    public String readString() {
        String result = read();
        return result;
    }

    /**
     * Read Integer og console.
     * @return int if message contains only Integer, otherwise print error.
     */
    public int readInt() {
        read();
        int result = 0;
        try {
            result = Integer.parseInt(message);
        } catch (NumberFormatException ex) {
            System.err.println("The argument: " + "\"" + message + "\"" + " is not of type Integer");
        }
        return result;
    }

    /**
     * Read Double og console.
     * @return double if message contains only numbers, otherwise print error.
     */
    public Double readDouble() {
        read();
        double result = 0;
        try {
            result = Double.parseDouble(message);
        } catch (NumberFormatException ex) {
            System.err.println("The argument: " + "\"" + message + "\"" + " is not of type Double");
        }
        return result;
    }

    /**
     * Read Float og console.
     * @return float if message contains only numbers, otherwise print error.
     */
    public Float readFloat() {
        read();
        float result = 0;
        try {
            result = Float.parseFloat(message);
        } catch (NumberFormatException ex) {
            System.err.println("The argument: " + "\"" + message + "\"" + " is not of type Float");
        }
        return result;
    }

    /**
     * Closed Scanner.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Read from console.
     * @return
     */
    private String read() {
        message = scanner.nextLine();
        return message;
    }
}
