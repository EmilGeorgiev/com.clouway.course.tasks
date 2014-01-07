package com.clouway.systemsms;

/**
 * Created by clouway on 12/27/13.
 */
public class Message {
  private final String recipient;
  private final String title;
  private final String text;

  public Message(String recipient, String title, String text) {

    this.recipient = recipient;
    this.title = title;
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public String getTitle() {
    return title;
  }

  public String getRecipient() {
    return recipient;
  }
}
