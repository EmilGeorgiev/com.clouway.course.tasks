package com.clouway.suportingmultipleclients.fake;

import com.clouway.suportingmultipleclients.ClientMessageListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
* Created by clouway on 2/12/14.
*/
public class MockClientMessageListener implements ClientMessageListener {

  public List<String> listMessages = new CopyOnWriteArrayList<String>();

  @Override
  public void newResponseWasReceived(String message) {
    listMessages.add(message);
  }
}
