package com.clouway.pagebean;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/10/13
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class DemoPAgeBean {
    public static void main(String[] args) {
        File fileName = new File("collections");
        PageBean pages = new PageBean(fileName);
        pages.monitor();
    }
}
