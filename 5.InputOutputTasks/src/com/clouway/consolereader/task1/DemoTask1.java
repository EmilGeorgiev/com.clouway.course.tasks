package com.clouway.consolereader.task1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/10/13
 * Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class DemoTask1 {
    public static void main(String[] args) {
        File file = new File("In");
        FileInputStream input = null;
        try {
            input = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Reader reader = new Reader(System.in);
        System.out.println("enter number of type Integer.");
        System.out.println(reader.readInt());
        System.out.println("enter number of type Double.");
        System.out.println(reader.readDouble());
        System.out.println("enter number of type Float.");
        System.out.println(reader.readFloat());
        System.out.println("enter same message.");
        System.out.println(reader.readString());

        reader.close();
    }
}
