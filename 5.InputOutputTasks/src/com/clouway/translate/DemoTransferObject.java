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
        TransferObject trnsfer = new TransferObject();
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream("In");
            out = new FileOutputStream("Out");
            System.out.println(trnsfer.transfer(in, out, 6, 100));
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }

    }
}
