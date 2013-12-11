package com.clouway.reversefile;


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
        buff = new StringBuffer();
        this.fileName = fileName;

    }


    /**
     * Reverses the contents of a text file.
     * The contents of the text file is read and put in a buffer.
     * The content of the buffer is reversed and then is recorded as a new content file.
     */
    public void reverse() {
        try {
            input = new BufferedReader(new FileReader(fileName));
            String line = input.readLine();
            while (line != null) {
                buff.append(line);
                line = input.readLine();
            }
            input.close();

            buff.reverse();
            output = new BufferedWriter(new FileWriter(fileName));
            output.write(buff.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (output != null) {
                try {
                    output.flush();
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
