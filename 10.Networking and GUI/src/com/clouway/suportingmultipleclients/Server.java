package com.clouway.suportingmultipleclients;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by clouway on 2/11/14.
 */
public class Server {
  private final ServerMessages serverMessages;
  private final ServerMessageListener serverMessageListener;
  private final int port;
  private ServerSocket serverSocket;
  private int countClients = 1;
  private List<Socket> listClients = new CopyOnWriteArrayList<Socket>();

  public Server(ServerMessages serverMessages, ServerMessageListener serverMessageListener, int port) {
    this.serverMessages = serverMessages;

    this.serverMessageListener = serverMessageListener;
    this.port = port;
  }

  public void startServer() {
    Socket clientSocket = null;
    try {
      serverSocket = new ServerSocket(port);
      while (true) {
        clientSocket = serverSocket.accept();

        serverMessageListener.newClientWasConnected(serverMessages.connectNewClient(countClients));

        ClientSocketThread clientSocketThread = new ClientSocketThread(listClients, clientSocket, serverMessages, countClients);
        clientSocketThread.start();
        Thread.sleep(200);
        countClients++;
      }

    } catch (SocketException e) {

    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
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

  public void stopServer() {
    try {
      if (serverSocket != null) {
        serverSocket.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
