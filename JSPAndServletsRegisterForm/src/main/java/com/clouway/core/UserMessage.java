package com.clouway.core;

/**
 * Created by clouway on 7/1/14.
 */
public class UserMessage {
  private final String fieldValue;
  private final String authenticateMessage;

  public UserMessage(String authenticateMessage, String fieldValue) {
    this.authenticateMessage = authenticateMessage;

    this.fieldValue = fieldValue;
  }

  public String getFieldValue() {
    return fieldValue;
  }

  public String getAuthenticateMessage() {
    return authenticateMessage;
  }
}
