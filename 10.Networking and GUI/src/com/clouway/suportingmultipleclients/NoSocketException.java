package com.clouway.suportingmultipleclients;

/**
 * Created by clouway on 2/12/14.
 */
public class NoSocketException extends RuntimeException {
  public NoSocketException() {
    System.err.println("Server closed");
  }
}
