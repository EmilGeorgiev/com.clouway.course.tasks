package com.clouway.consolereader.classes;

import com.clouway.consolereader.validator.Validator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class ScannerIn implements Validator {

    private final String BREAK_POINT = ".";
    private final String fileName;
    private static Scanner scann;

    public ScannerIn(String fileName) {
        this.fileName = fileName;
        scann = new Scanner(System.in);
    }

    /**
     * Read from the console until introduce BREAK_POINT.
     * @throws IOException
     */
    public void read()throws IOException{
        String line;
        while (!(line = scann.nextLine()).equals(BREAK_POINT)) {
            if (this.validate(line)) {
                save(line);
            }
        }
    }

    /**
     * Closed Scanner.
     */
    public void close() {
        scann.close();
    }

    /**
     * Saved reading from the console to a file.
     * @param message what written to a file.
     */
    private void save(String message) {
        BufferedWriter buffWriter = null;
        FileWriter file = null;
        try {
            file = new FileWriter(fileName);
            buffWriter = new BufferedWriter(file);
            buffWriter.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if (buffWriter != null) {
                try {
                    buffWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("The writer isn't open.");
            }
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("The file isn't open.");
            }
        }
    }

}
