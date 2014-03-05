package com.clouway.suportingmultipleclients;

/**
 * Created by clouway on 2/17/14.
 */
public interface ClientMessageListener {

  void newResponseWasReceived(String message);
}
