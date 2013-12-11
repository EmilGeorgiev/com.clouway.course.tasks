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
    static final String BREAK_POINT = "q";
    static final String NEXT = "next";
    static final String PREVIOUS = "previous";


    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        File file = new File("collections");
        PageBean page = new PageBean(file);
        String line;
        while (!(line = scann.nextLine()).equals(BREAK_POINT)) {
            if(line.equalsIgnoreCase(NEXT)) {
                page.next();
            } else if (line.equalsIgnoreCase(PREVIOUS)) {
                page.previous();
            }
        }
        scann.close();
    }


}
