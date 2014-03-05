package com.clouway.downloadagent;

public class DownloadPageDemo {
  public static void main(String[] args) {
    DownloadAgent downloadAgent = new DownloadAgent();
    DownloadProgressListener view = new DownloadViewListener(downloadAgent);
    downloadAgent.setDownloadProgressListener(view);
  }
}
