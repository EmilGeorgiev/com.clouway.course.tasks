package com.clouway.translate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/6/13
 * Time: 10:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class TransferObject {


    /**
     * Transfer the contents of an instance of InputStream in an instance of OutputStream
     * @param in ins InputStream.
     * @param out is OutputStream.
     * @param numberOfBytes determines how many bytes to be transferred. If the parameter is -1, then the method must read all bytes.
     * @param offset determines how many bytes from the beginning of the input stream to be skipped before starting the transfer output to.
     * @return returns the number of bytes transferred
     * @throws IOException
     */
    public int transfer(InputStream in, OutputStream out, int numberOfBytes, int offset) throws IOException {
        byte[] buff = new byte[15];
        int counter = 0;
        int bytes;
        if ((offset < 0) && numberOfBytes < -1) {
            throw new IllegalArgumentException();
        }
        in.skip(offset);
        if (numberOfBytes != -1) {
            int byteNumber = numberOfBytes;
            while(((bytes = in.read(buff)) != -1) && (byteNumber > 0)) {
                out.write(buff, 0, bytes);
                out.flush();
                byteNumber -= bytes;
                counter += bytes;
            }
        } else {
            while((bytes = in.read(buff)) != -1) {
                out.write(buff, 0, bytes);
                out.flush();
                counter += bytes;
            }
        }
        return counter;
    }
}
