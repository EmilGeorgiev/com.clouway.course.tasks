package com.clouway.downloadagent;

import com.clouway.translate.TransferObject;

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class DownloadAgent {

  private int currentSizeOfFile;
  private int sizeDownloadPage;
  private TransferObject transfer = new TransferObject();

  private DownloadView view;

  public void setView(DownloadView view) {
    this.view = view;
  }

  public void downloadFile(final String filePath, String pageAddress) {
    BufferedReader reader = null;
    OutputStreamWriter writer = null;
    try {
      URL address = new URL(pageAddress);
      final URLConnection connection = address.openConnection();
      connection.connect();
      InputStream inputStream = connection.getInputStream();
      InputStreamReader input = new InputStreamReader(inputStream);
      reader = new BufferedReader(input);
      OutputStream outputStream = new FileOutputStream(filePath);
      writer = new OutputStreamWriter(outputStream);

     sizeDownloadPage = connection.getContentLength();
     ProgressBar thread = new ProgressBar(view, sizeDownloadPage, filePath);
      thread.start();
     transfer.transfer(inputStream, outputStream, -1, 0);

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
//http://www.oracle.com/