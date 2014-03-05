package com.clouway.suportingmultipleclients.fake;

import com.clouway.suportingmultipleclients.ServerMessageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by clouway on 2/19/14.
 */
public class MockServerMessageListener implements ServerMessageListener{


  public List<String> listMessage = new ArrayList<String>();

  private ArrayBlockingQueue<Boolean> waitQueue = new ArrayBlockingQueue<Boolean>(2);

  @Override
  public void newClientWasConnected(String message) {
    listMessage.add(message);
    waitQueue.add(true);
  }

  public List<String> getServerDisplayMessages() {
    try {
      waitQueue.take();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return listMessage;
  }
}
