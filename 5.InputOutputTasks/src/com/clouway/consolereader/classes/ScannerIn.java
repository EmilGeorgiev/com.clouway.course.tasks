package com.clouway.consolereader.classes;

import com.clouway.consolereader.validator.Validator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class ScannerIn implements Validator {

    private final String BREAK_POINT = ".";
    private final String fileName;
    private Scanner scann;
    private String line;


    public ScannerIn(String fileName) {
        this.fileName = fileName;
        line = null;
        scann = null;
    }

    public void read()throws IOException{
        FileWriter file = null;
        BufferedWriter buffWriter = null;
        try {
            scann = new Scanner(System.in);
            file = new FileWriter(fileName);
            buffWriter = new BufferedWriter(file);
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
            if (file != null) {
                file.close();
            } else {
                System.out.println("The scann isn't open.");
            }
        }
    }

}
