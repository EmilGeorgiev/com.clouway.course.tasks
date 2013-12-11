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
    final String FINISH = "finish";

    public String read() {
        Scanner scann = new Scanner(System.in);
        String line;
        while (!(line = scann.nextLine()).equals(BREAK_POINT)) {
            try {
                FileWriter writer = new FileWriter("ofconsole");
                writer.write(line);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return FINISH;
    }
}
