package com.clouway.consolereader.classes;

import com.clouway.consolereader.validator.Validator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class ScannerIn {

    private final String BREAK_POINT = ".";
    private final String fileName;
    private Scanner scann;
    private BufferedWriter buffWriter;
    private String line;


    public ScannerIn(String fileName) {
        this.fileName = fileName;
        line = null;
        scann = null;
    }

    public void read(Validator valid)throws IOException{
        try {
            scann = new Scanner(System.in);
            buffWriter = new BufferedWriter(new FileWriter(fileName));
            while (!(line = scann.nextLine()).equals(BREAK_POINT)) {
                if (valid.validate(line)) {
                    buffWriter.write(line);
                }
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
