package com.clouway.suportingmultipleclients;

/**
 * Created by clouway on 2/17/14.
 */
public interface ServerMessageListener {

  void newClientWasConnected(String message);
}
