package com.clouway.clientserver.fake;

import com.clouway.clientserver.ServerMessageListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clouway on 2/19/14.
 */
public class MockServerMessageListener implements ServerMessageListener {

  public List<String> listMessages = new ArrayList<String>();

  @Override
  public void newClientWasConnected(String message) {
    listMessages.add(message);
  }
}
