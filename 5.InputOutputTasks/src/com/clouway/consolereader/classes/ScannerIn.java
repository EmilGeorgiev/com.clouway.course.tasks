package com.clouway.consolereader.classes;

import com.clouway.consolereader.validator.Validator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class ScannerIn implements Validator {

    private final String BREAK_POINT = ".";
    private final String fileName;
    private String line;


    public ScannerIn(String fileName) {
        this.fileName = fileName;
        line = null;
    }

    public void read()throws IOException{
        Scanner scann = null;
        BufferedWriter buffWriter = null;
        try {
            scann = new Scanner(System.in);
            buffWriter = new BufferedWriter(new FileWriter(fileName));
            line = scann.nextLine();
            while (!line.equals(BREAK_POINT)) {
                if (this.validate(line)) {
                    buffWriter.write(line);
                }
                line = scann.nextLine();
            }
        } finally {
            if (buffWriter != null) {
               buffWriter.close();
            } else {
                System.out.println("The writer isn't open.");
            }
            if (scann != null) {
                scann.close();
            } else {
                System.out.println("The scann isn't open.");
            }
        }
    }

}
