package com.clouway.clientserver;

/**
 * Created by clouway on 2/12/14.
 */
public interface ServerMessages {

  String gatHello();

  String startServer();

  String acceptClient();

  String sendMessage();
}
