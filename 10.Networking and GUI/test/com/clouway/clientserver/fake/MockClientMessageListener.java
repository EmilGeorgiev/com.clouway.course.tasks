package com.clouway.clientserver.fake;

import com.clouway.clientserver.ClientMessageListener;

import java.util.ArrayList;
import java.util.List;

/**
* Created by clouway on 2/12/14.
*/
public class MockClientMessageListener implements ClientMessageListener {

  public List<String> listMessages = new ArrayList<String>();

  @Override
  public void onNewMessageReceived(String message) {
    listMessages.add(message);
  }
}
