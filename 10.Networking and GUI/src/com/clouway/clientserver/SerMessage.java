package com.clouway.clientserver;

/**
 * Created by clouway on 2/12/14.
 */
public class SerMessage implements ServerMessages {

  @Override
  public String gatHello() {
    return "Hello ";
  }

  @Override
  public String startServer() {
    return "Server starting on port 4444 and listener for required.";
  }

  @Override
  public String acceptClient() {
    return "Server accept new client.";
  }

  @Override
  public String sendMessage() {
    return "Server send message to client";
  }
}
