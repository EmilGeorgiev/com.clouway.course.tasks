package com.clouway.clientserver;

/**
 * Created by clouway on 2/12/14.
 */
public class ClientMain  {

  public static void main(String[] args) {
    ClientView clientView = new ClientView();

    UserMessages message = new UsMessages();

    Client client = new Client(clientView, message, 4444);
    client.connectToServer();
  }


}
