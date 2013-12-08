package com.clouway.serialization;

import java.io.InputStream;
import java.io.OutputStream;

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
     * @param out
     * @param o
     */
    public void saveObject(OutputStream out, Object o) {

    }

    /**
     * Read by instructions InputStream instance of arbitrary class and return it as a result. If no such file - throws IOException.
     * @param in
     * @return
     */
    public Object getObject(InputStream in) {
        return "Emil";
    }
}
