package com.clouway.pagebean;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/11/13
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class Command {

    final char NEXT = 'n';
    final char PREVIOUS = 'p';
    final char FIRST = 'f';
    final char LAST = 'l';
    final char GET = 'g';
    final char HAS_NEXT = 'h';
    final int INDEX = 0;
    private File file;
    private PageBean pages;

    public Command(String fileName) {
        file = new File(fileName);
        pages = new PageBean(file);
    }

    public void chechComand(String line) {
        switch (line.charAt(INDEX)) {
            case NEXT: pages.next();
                break;
            case PREVIOUS: pages.previous();
                break;
            case FIRST: pages.firstPage();
                break;
            case LAST: pages.lastPage();
                break;
            case GET: pages.getCurrentPageNumber();
                break;
            case HAS_NEXT: pages.hasNext();
                break;
            default: break;
        }
    }
}
