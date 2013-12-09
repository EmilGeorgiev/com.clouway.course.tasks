package com.clouway.serialization;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/6/13
 * Time: 4:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataClass {

    /**
     * Saved the parameter "o" in the specified output stream.
     *
     * @param out output stream.
     * @param o object which remains.
     */
    public void saveObject(OutputStream out, Object o) {
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(out);
            output.writeObject(o);
            System.out.printf("Serialized data is saved in person.ser");
        } catch (IOException i) {
            i.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Read by instructions InputStream instance of arbitrary class and return it as a result. If no such file - throws IOException.
     *
     * @param in input stream
     * @return instance of the class read.
     */
    public Object getObject(InputStream in) {
        ObjectInputStream input = null;
        Object object = null;
        try {
            input = new ObjectInputStream(in);
            object = input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }
}
