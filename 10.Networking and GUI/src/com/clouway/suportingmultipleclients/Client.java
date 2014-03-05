package com.clouway.suportingmultipleclients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by clouway on 2/12/14.
 */
public class Client {
  private final UserMessages userMessages;
  private final ClientMessageListener clientMessageListener;
  private final int port;
  private StringBuilder response = new StringBuilder();
  private ArrayBlockingQueue<Boolean> waitingQueue = new ArrayBlockingQueue<Boolean>(1);

  public Client(UserMessages userMessages, ClientMessageListener clientMessageListener, int port) {

    this.userMessages = userMessages;
    this.clientMessageListener = clientMessageListener;
    this.port = port;
  }

  public void connectToServer() {
    try {
      //Connect to a socket server with specific por
      Socket clientSocket;
      while (true) {
        try {
          clientSocket = new Socket("localhost", port);
          break;
        } catch (ConnectException ex) {

        }
      }
      clientMessageListener.newResponseWasReceived(userMessages.connectClient());

      InputStream inputStream = clientSocket.getInputStream();
      BufferedReader scanner = new BufferedReader(new InputStreamReader(inputStream));

      String line;
      while (true) {
        line = scanner.readLine();
        if (line == null) {
          clientSocket.close();
          throw new NoSocketException();
        }

        response.append(line);
        waitingQueue.add(true);

      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getResponse() {
    try {
      waitingQueue.take();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return response.toString();
  }
}
