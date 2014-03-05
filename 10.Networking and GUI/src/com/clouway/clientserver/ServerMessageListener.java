package com.clouway.clientserver;

/**
 * Created by clouway on 2/19/14.
 */
public interface ServerMessageListener {

  void newClientWasConnected(String message);
}
