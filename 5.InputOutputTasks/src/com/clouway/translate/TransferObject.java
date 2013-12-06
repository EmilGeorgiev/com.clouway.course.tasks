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
        byte[] buff = new byte[1];
        int counter = 0;
            in.skip(offset);
            if (numberOfBytes != -1) {
                int byteNumber = numberOfBytes;
                while((in.read(buff) != -1) && (byteNumber > 0)) {
                    out.write(buff);
                    out.flush();
                    byteNumber--;
                    counter++;
                }
            } else {
                while(in.read(buff) != -1) {
                    out.write(buff);
                    out.flush();
                    counter++;
                }
            }

        return counter;
    }
}
