package com.clouway.suportingmultipleclients;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Created by clouway on 2/11/14.
 */
public class ClientSocketThread extends Thread{
  private final List<Socket> listClients;
  private final Socket clientSocket;
  private final ServerMessages serverMessages;
  private final int countClients;
  private PrintWriter writer;

  public ClientSocketThread(List<Socket> listClients, Socket clientSocket, ServerMessages serverMessages, int countClients) {

    this.listClients = listClients;
    this.clientSocket = clientSocket;
    this.serverMessages = serverMessages;
    this.countClients = countClients;
  }

  @Override
  public void run() {
    try {
      writer = new PrintWriter(clientSocket.getOutputStream(), true);

      writer.println(serverMessages.sendFirstMessageToNewClient(countClients));


      new Thread(new Runnable() {
        @Override
        public void run() {
          for (Socket client : listClients) {
            try {
              writer = new PrintWriter(client.getOutputStream(), true);
            } catch (IOException e) {
              e.printStackTrace();
            }
            writer.println(serverMessages.sendMessageToAllClient(countClients));
          }

          listClients.add(clientSocket);

        }
      }).start();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
