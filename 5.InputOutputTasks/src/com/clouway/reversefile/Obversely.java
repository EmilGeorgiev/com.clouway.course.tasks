package com.clouway.reversefile;

import com.sun.java_cup.internal.runtime.Scanner;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/6/13
 * Time: 9:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class Obversely {
    private BufferedReader input;
    private StringBuffer buff;
    private BufferedWriter output;
    private String fileName;

    public Obversely(String fileName) {
       input = null;
        output = null;
        buff = new StringBuffer();
        this.fileName = fileName;

    }

    public void reverse() {
        try {
            input = new BufferedReader(new FileReader(fileName));
            String  line = input.readLine();
            while (line != null) {
                buff.append(line);
                line = input.readLine();
            }
            input.close();

            buff.reverse();
            output = new BufferedWriter(new FileWriter(fileName));
            output.write(buff.toString());
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

        }
    }
}
