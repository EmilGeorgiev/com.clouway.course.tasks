package com.clouway.downloadagent;

/**
 * Created by clouway on 1/28/14.
 */
public interface DownloadProgressListener {

  void onProgressWasUpdate(int savedBytes, long sizeDownloadFile);
}
