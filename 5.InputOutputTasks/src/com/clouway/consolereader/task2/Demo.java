package com.clouway.consolereader.task2;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/10/13
 * Time: 5:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String breakPoint;
        System.out.println("Please enter symbol in the program complete");
        breakPoint = scanner.nextLine();
        ConsoleRaeder reader = new ConsoleRaeder(scanner, breakPoint);
        reader.read();
    }
}
