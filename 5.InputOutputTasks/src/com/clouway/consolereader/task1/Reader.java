package com.clouway.consolereader.task1;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/10/13
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Reader {

    private Scanner scann;
    private String message;

    public Reader() {
        scann = new Scanner(System.in);
    }

    /**
     * Read String of console.
     * @return String.
     */
    public String readString() {
        System.out.println("enter same message.");
        String result = read();
        return result;
    }

    /**
     * Read Integer og console.
     * @return int if message contains only Integer, otherwise print error.
     */
    public int readInt() {
        System.out.println("enter number of type Integer.");
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
        System.out.println("enter number of type Double.");
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
        System.out.println("enter number of type Float.");
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
        scann.close();
    }

    /**
     * Read from console.
     * @return
     */
    private String read() {
        message = scann.nextLine();
        return message;
    }
}
