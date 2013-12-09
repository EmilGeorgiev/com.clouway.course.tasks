package com.clouway.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/6/13
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class DemoDataClass {
    public static void main(String[] args) {
        Person person = new Person("Emil", "Ivan Vazov", 112233, 25);
        DataClass data = new DataClass();
        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            output = new FileOutputStream("person.ser");
            input = new FileInputStream("person.ser");
            data.saveObject(output, person);
            person = (Person) data.getObject(input);
            System.out.println(person.toString());
        } catch (IOException i) {
            i.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (output != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
