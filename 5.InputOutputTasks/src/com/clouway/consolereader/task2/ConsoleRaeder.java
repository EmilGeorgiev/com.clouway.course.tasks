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
    private String fileName;
    private String breakPoint;
    private Scanner scanner;

    public ConsoleRaeder(Scanner scanner ,String breakPoint, String fileName) {
        this.scanner = scanner;
        this.breakPoint = breakPoint;
        this.fileName = fileName;
    }

    /**
     * Read message from input stream.
     */
    public void read() {
        String line;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, true);
            while (!(line = scanner.nextLine()).equals(breakPoint)) {
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
