package com.clouway.consolereader.classes;

import com.clouway.consolereader.validator.Validator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class ScannerIn implements Validator {

    private final String BREAK_POINT = ".";
    private Scanner scann;
    private BufferedWriter buffWriter;
    private FileWriter fileWriter;
    private String line;

    public ScannerIn(String fileName) {
        line = null;
        scann = new Scanner(System.in);
        try {
            fileWriter = new FileWriter(fileName);
        } catch (IOException e) {

        }
        buffWriter = new BufferedWriter(fileWriter);
    }

    public void read(Validator valid)throws IOException{
        try {
            while (!(line = scann.nextLine()).equals(BREAK_POINT)) {
                if (this.validate(line)) {
                    buffWriter.write(line);
                }
            }
        } finally {
            if (buffWriter != null) {
               buffWriter.close();
            } else {
                System.out.println("The writer isn't open.");
            }
            if (fileWriter != null) {
                fileWriter.close();
            } else {
                System.out.println("The fileWriter isn't open.");
            }
        }
    }

}
