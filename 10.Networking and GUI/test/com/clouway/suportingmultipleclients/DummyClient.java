package com.clouway.suportingmultipleclients;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by clouway on 2/14/14.
 */
public class DummyClient {

  private StringBuilder response = new StringBuilder();
  private final int port;
  private ArrayBlockingQueue<Boolean> waitQueue = new ArrayBlockingQueue<Boolean>(1);

  public DummyClient(int port) {

    this.port = port;

  }

  public void connect() {

    new Thread(new Runnable() {
      @Override
      public void run() {
        Socket clientSocket = null;
        try {
          while (true) {
            try {
              clientSocket = new Socket("localhost", port);
              break;
            } catch (ConnectException ex) {

            }
          }

          InputStream inputStream = clientSocket.getInputStream();
          Scanner scanner = new Scanner(inputStream);

          while (scanner.hasNext()) {
            response.append(scanner.nextLine());
            waitQueue.add(true);

          }

        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          if (clientSocket != null) {
            try {
              clientSocket.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      }
    }).start();

  }

  public String getResponse() {
      try {
        waitQueue.take();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    return response.toString();
  }
}
