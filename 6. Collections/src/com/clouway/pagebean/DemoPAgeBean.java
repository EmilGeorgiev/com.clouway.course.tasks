package com.clouway.pagebean;

import java.io.File;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/10/13
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class DemoPAgeBean {
    static final String NEXT = "next";
    static final String PREVIOUS = "previous";
    static final String BREAK_POINT = "q";

    public static void main(String[] args) {
        File fileName = new File("collections");
        PageBean pages = new PageBean(fileName);
        Scanner scann = new Scanner(System.in);

        String line;
        while (!(line = scann.nextLine()).equals(BREAK_POINT)) {
            if (line.equalsIgnoreCase(NEXT)) {
                pages.next();
            } else if(line.equalsIgnoreCase(PREVIOUS)) {
                pages.previous();
            }
        }
        scann.close();
    }
}
