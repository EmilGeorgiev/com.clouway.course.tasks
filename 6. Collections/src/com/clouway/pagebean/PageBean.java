package com.clouway.pagebean;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/10/13
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class PageBean {

    final int PAGE_SIZE = 10;
    private ArrayList<String[]> listOfPages;

    public PageBean(File fileNAme) {
        dividedPages(fileNAme);
    }



    /**
     * Dividing the text pages
     * @param fileName file which contains the text.
     */
    private void dividedPages(File fileName) {
        String[] page;
        Scanner scann = null;
        listOfPages = new ArrayList<String[]>();
        try {
            scann = new Scanner(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scann.hasNext()) {
            page = new String[PAGE_SIZE];
            for (int i = 0; i < PAGE_SIZE; i++) {
                if (!scann.hasNext()) {
                    break;
                }
                page[i] = scann.next();
            }
            listOfPages.add(page);
        }
    }
}
