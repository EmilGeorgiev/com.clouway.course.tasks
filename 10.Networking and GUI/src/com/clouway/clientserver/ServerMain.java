package com.clouway.clientserver;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by clouway on 2/12/14.
 */
public class ServerMain {
  public static void main(String[] args) {

    Clock clock = new Clock() {
      @Override
      public Date now() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
      }
    };

    ServerView serverView = new ServerView();

    ServerMessages serverMessages = new SerMessage();

    Server server = new Server(serverView, serverMessages, clock, 4444);

    serverView.setServer(server);

    server.startServer();
  }
}
