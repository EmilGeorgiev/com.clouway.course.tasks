package com.clouway.consolereader.task1;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/10/13
 * Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class DemoTask1 {
    public static void main(String[] args) {
        Reader reader = new Reader();
        reader.readInt();
        reader.readDouble();
        reader.readFloat();
        reader.readString();

        reader.close();
    }
}
