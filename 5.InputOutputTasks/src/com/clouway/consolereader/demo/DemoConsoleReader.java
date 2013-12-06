package com.clouway.consolereader.demo;

import com.clouway.consolereader.classes.RaederInt;
import com.clouway.consolereader.classes.ReaderDouble;
import com.clouway.consolereader.classes.ReaderFloat;
import com.clouway.consolereader.classes.ReaderString;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/5/13
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class DemoConsoleReader {
    public static void main(String[] args) {
        ReaderString inString = new ReaderString("InputString");
        RaederInt inInteger = new RaederInt("InputInteger");
        ReaderDouble inDouble = new ReaderDouble("InputDouble");
        ReaderFloat inFloat = new ReaderFloat("InputFloat");

        try {
            inString.read(inString);
            inInteger.read(inInteger);
            inDouble.read(inDouble);
            inFloat.read(inFloat);
        } catch (IOException ex) {

        }

    }
}
