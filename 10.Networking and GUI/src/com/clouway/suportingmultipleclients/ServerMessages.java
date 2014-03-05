package com.clouway.suportingmultipleclients;

/**
 * Created by clouway on 2/11/14.
 */
public interface ServerMessages {
  String connectNewClient(int countClients);

  String sendFirstMessageToNewClient(int countClients);

  String sendMessageToAllClient(int countClients);
}
