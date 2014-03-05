package com.clouway.downloadagent;

import com.clouway.translate.TransferObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadAgent {

  private TransferObject downloadObject;
  private DownloadProgressListener downloadProgressListener;

  public void setDownloadProgressListener(DownloadProgressListener downloadProgressListener) {
    this.downloadProgressListener = downloadProgressListener;
  }

  /**
   * Download from the internet and stores it in another file.
   * For downloaded file using "TransferObject.class" by "com.clouway.translate"
   *
   * @param filePath    The file that stories the downloaded file.
   * @param pageAddress Address of the remote file.
   */
  public void downloadFile(final String filePath, String pageAddress) {
    BufferedReader reader = null;
    OutputStreamWriter writer = null;
    try {
      //Create URL and retrieve URLConnection object.
      URL address = new URL(pageAddress);
      final URLConnection connection = address.openConnection();
      connection.connect();

      InputStream inputStream = connection.getInputStream();
      InputStreamReader input = new InputStreamReader(inputStream);
      reader = new BufferedReader(input);

      OutputStream outputStream = new FileOutputStream(filePath);
      writer = new OutputStreamWriter(outputStream);

      this.downloadObject = new TransferObject(downloadProgressListener, connection.getContentLength());
      downloadObject.transfer(inputStream, outputStream, -1, 0);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (writer != null) {
        try {
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}