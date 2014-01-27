package com.clouway.downloadagent;

import java.util.Scanner;

public class DownloadPageDemo {
  public static void main(String[] args) {
    DownloadAgent downloadAgent = new DownloadAgent();
    DownloadView view = new DownloadView(downloadAgent);
    downloadAgent.setView(view);

  }
}
