package com.clouway.translate;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/6/13
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class DemoTransferObject {
    public static void main(String[] args) {
        TransferObject transfer = new TransferObject();
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream("In");
            out = new FileOutputStream("Out");
            int readBytes = transfer.transfer(in, out, -1, 8);
            System.out.println(readBytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("The offset isn't negative number and numberOfBytes isn't less -1.");
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
