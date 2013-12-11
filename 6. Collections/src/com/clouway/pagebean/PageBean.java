package com.clouway.pagebean;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/10/13
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class PageBean {

    private final int PAGE_SIZE = 10;
    private ArrayList<String[]> listOfPages;
    private int temp;

    public PageBean(File fileNAme) {
        dividedPages(fileNAme);
        temp = -1;
    }

    /**
     * Show next page.If there is no next page display error message.
     * If the last page of the list is less than PAGE_SIZE element does not receive an  error.
     */
    public void next() {
        if (temp < (listOfPages.size() - 1)){
            temp++;
            printPage();
        } else {
            System.out.println("No next pages.");
        }
    }

    /**
     * Show previous page.If there is no previous page display message.
     */
    public void previous() {
        if (temp == 0) {
            System.out.println("No previous pages.");
        } else {
            temp--;
            printPage();
        }
    }

    /**
     * Check whether the following elements.
     * @return Returns true if the following elements, or false if there is no.
     */
    public boolean hasNext() {
        if (temp >= (listOfPages.size() -1)) {
            System.out.println("No next pages.");
            return false;
        }
        return true;
    }

    /**
     * Check previous pages have.
     * @return Returns true if the previous pages, or false if there is no.
     */
    public boolean hasPrevious() {
        if (temp <= 0) {
            System.out.println("\nNo previous pages.");
            return false;
        }
        return true;
    }

    /**
     * Returns the first page and make it current.
     */
    public void firstPage() {
        temp = 0;
        printPage();
    }

    /**
     * Returns the last page and makes it current
     */
    public void lastPage() {
        temp = (listOfPages.size() -1);
        printPage();
    }

    /**
     * Show number of the current page.
     * @return the current page numbers.
     */
    public int getCurrentPageNumber() {
        return temp;
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scann.close();
        }
    }

    private void printPage() {
        System.out.print("\nPage " + (temp + 1) + ":");
        for (String word : listOfPages.get(temp)) {
            if (word != null) {
                System.out.print(word + " ");
            }
        }
    }
}
