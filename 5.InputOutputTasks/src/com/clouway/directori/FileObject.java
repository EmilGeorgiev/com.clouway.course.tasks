package com.clouway.directori;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/12/13
 * Time: 10:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileObject {

    private String name;
    private String type;

    public FileObject(String type, String name) {
        this.name = name;
        this.type = type;
    }

    public void printInfo() {
        System.out.println(type + " " + name);
    }
}
