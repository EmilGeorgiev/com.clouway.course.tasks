package com.clouway.util;

import com.clouway.core.JettyServer;

/**
 * @author Emil Georgiev <emogeorgiev88@gmail.com>
 */
public class TestServer {
  public static void main(String[] args) {
    try {
      JettyServer.main(args);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
