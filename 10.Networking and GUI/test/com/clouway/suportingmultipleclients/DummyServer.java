package com.clouway.suportingmultipleclients;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by clouway on 2/12/14.
 */
public class DummyServer {
  private final String message;
  private final int port;
  private ServerSocket serverSocket = null;

  public DummyServer(String message, int port) {

    this.message = message;
    this.port = port;
  }

  public void startServer() {

    Socket clientSocket = null;
      try {
        serverSocket = new ServerSocket(port);
        while (true) {

          clientSocket = serverSocket.accept();
          OutputStream outputStream = clientSocket.getOutputStream();
          PrintWriter writer = new PrintWriter(outputStream, true);
          writer.println(message);
        }


      }catch (SocketException e) {

      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          if (clientSocket != null) {
            clientSocket.close();
          }
          stop();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

  public void stop() {
    try {
      if (serverSocket != null) {

        serverSocket.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
