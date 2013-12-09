package com.clouway.serialization;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/6/13
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class DemoDataClass {
    public static void main(String[] args) {
        Person person = new Person("Emil", "Vasil Levski", 1112233, 25);
        DataClass data = new DataClass();
        FileOutputStream fileOut = null;
        FileInputStream  fileIn = null;

        try {

            fileOut = new FileOutputStream("person.ser");
            fileIn = new FileInputStream("person.ser");
            data.saveObject(fileOut, person);
            person = (Person) data.getObject(fileIn);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (fileIn != null) {
                try {
                    fileIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Person name is: " + person.name);
        System.out.println("Person address is: " + person.address);
        System.out.println("Person ssh is: " + person.SSH);
        System.out.println("Person age is: " + person.age);


    }
}
