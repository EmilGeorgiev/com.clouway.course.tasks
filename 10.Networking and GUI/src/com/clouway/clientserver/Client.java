package com.clouway.clientserver;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by clouway on 2/12/14.
 */
public class Client {

  private final UserMessages userMessages;
  private final int port;
  private final ClientMessageListener clientMessageListener;
  private StringBuilder builder = new StringBuilder();

  public Client(ClientMessageListener clientMessageListener, UserMessages userMessages, int port) {
    this.clientMessageListener = clientMessageListener;
    this.userMessages = userMessages;
    this.port = port;
  }

  public void connectToServer() {
    try {
      Socket clientSocket = new Socket("localhost", port);
      clientMessageListener.onNewMessageReceived(userMessages.connectClient());

      InputStream inputStream = clientSocket.getInputStream();

      Scanner scanner = new Scanner(inputStream);

      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        clientMessageListener.onNewMessageReceived(userMessages.readFromServer());

        System.out.println(line);
        clientMessageListener.onNewMessageReceived(userMessages.printMessage());

        builder.append(line);
      }

      clientSocket.close();
      clientMessageListener.onNewMessageReceived(userMessages.closeClient());

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public String getResponse() {

    return builder.toString();
  }

  public void stop() {

  }
}
