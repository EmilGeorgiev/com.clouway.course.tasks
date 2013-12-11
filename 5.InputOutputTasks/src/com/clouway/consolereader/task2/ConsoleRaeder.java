package com.clouway.consolereader.task2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/10/13
 * Time: 5:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConsoleRaeder {
    final String BREAK_POINT = ".";
    final String FILE_NAME = "ofconsole";
    final boolean ADDING = true;

    public void read() {
        Scanner scann = new Scanner(System.in);
        String line;

        try {
            while (!(line = scann.nextLine()).equals(BREAK_POINT)) {
                FileWriter writer = new FileWriter(FILE_NAME, true);
                writer.write(line);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scann.close();
        }

    }
}
