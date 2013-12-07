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
        if ((offset < 0) && numberOfBytes < -1) {
            throw new IllegalArgumentException();
        }
        in.skip(offset);
        byte[] buff = new byte[15];
        int counter = 0;
        int readBytes;
        if (numberOfBytes != -1) {
            int byteNumber = numberOfBytes;
            while(((readBytes = in.read(buff)) != -1) && (byteNumber > 0)) {
                if (readBytes > byteNumber) {
                     out.write(buff, 0, byteNumber);
                     counter += byteNumber;
                } else {
                    out.write(buff, 0, readBytes);
                    counter += readBytes;
                }
                out.flush();
                byteNumber -= readBytes;
            }
        } else {
            while((readBytes = in.read(buff)) != -1) {
                out.write(buff, 0, readBytes);
                out.flush();
                counter += readBytes;
            }
        }
        return counter;
    }
}
