package com.clouway.consolereader.task2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/10/13
 * Time: 5:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConsoleRaeder {
    final String FILE_NAME = "ofconsole";
    private String breakPoint;
    private Scanner scanner;

    public ConsoleRaeder(Scanner scanner ,String breakPoint) {
        this.scanner = scanner;
        this.breakPoint = breakPoint;
    }

    /**
     * Read message from input stream.
     */
    public void read() {
        String line;
        FileWriter writer = null;
        try {
            while (!(line = scanner.nextLine()).equals(breakPoint)) {
                writer = new FileWriter(FILE_NAME, true);
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.flush();
                writer.close();
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
