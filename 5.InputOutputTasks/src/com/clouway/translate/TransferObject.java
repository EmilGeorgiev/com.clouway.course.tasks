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

    final int SIZE_ARRAY = 34;

    /**
     * Transfer the contents of an instance of InputStream in an instance of OutputStream
     *
     * @param in            ins InputStream.
     * @param out           is OutputStream.
     * @param numberOfBytes determines how many bytes to be transferred. If the parameter is -1, then the method must read all bytes.
     * @param offset        determines how many bytes from the beginning of the input stream to be skipped before starting the transfer output to.
     * @return returns the number of bytes transferred
     * @throws IOException
     */
    public int transfer(InputStream in, OutputStream out, int numberOfBytes, int offset) {
        byte[] buff = new byte[SIZE_ARRAY];
        int bytesCount = 0;
        if ((offset < 0) || numberOfBytes < -1) {
            throw new IllegalArgumentException();
        }
        if (offset > buff.length) {
            System.out.println("Offset is larger than the size of the buffer.");
            return 0;
        }
        try {
            int readBytes;
            in.skip(offset);
            while (((readBytes = in.read(buff)) != -1)) {
                if (readBytes < numberOfBytes || numberOfBytes == -1) {
                    out.write(buff, 0, readBytes);
                    bytesCount += readBytes;
                } else {
                    out.write(buff, 0, numberOfBytes);
                    bytesCount += numberOfBytes;
                }
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytesCount;
    }
}
