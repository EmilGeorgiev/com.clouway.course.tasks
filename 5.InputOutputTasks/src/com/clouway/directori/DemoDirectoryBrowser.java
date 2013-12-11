package com.clouway.directori;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/6/13
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class DemoDirectoryBrowser {
    public static void main(String[] args) {
        DirectoryBrowser dir = new DirectoryBrowser();
        Scanner scann = new Scanner(System.in);
        System.out.println("Enter file path.");
        String filePath = scann.nextLine();

        dir.listContent(filePath);
    }
}
