package com.clouway.downloadagent;

import javax.swing.*;
import java.io.File;

/**
 * Created by clouway on 1/27/14.
 */
public class ProgressBar extends Thread {

  private final DownloadView view;
  private final int sizeDownloadPage;
  private final String filePath;
  private int currentSizeOfFile = 0;
  private File file;

  public ProgressBar(DownloadView view, int sizeDownloadPage, String filePath) {

    this.view = view;
    this.sizeDownloadPage = sizeDownloadPage;
    this.filePath = filePath;
    this.file = new File(filePath);
    view.getProgressBar().setMaximum(sizeDownloadPage);
  }

  @Override
  public void run() {
    view.getProgressBar().setValue(0);
    while ((currentSizeOfFile = (int) file.length()) <= sizeDownloadPage) {
          view.getProgressBar().setValue(currentSizeOfFile + 1);
    }
  }
}
