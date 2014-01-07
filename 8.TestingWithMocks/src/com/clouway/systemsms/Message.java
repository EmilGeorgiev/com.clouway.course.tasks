package com.clouway.systemsms;

/**
 * Created by clouway on 12/27/13.
 */
public class Message {
  private final String recipient;
  private final String title;
  private final String text;
  private final String SEND_MESSAGE = "Message is send";

  public Message(String recipient, String title, String text) {

    this.recipient = recipient;
    this.title = title;
    this.text = text;
  }

  public String send(ValidatorSystem system) {
    if (!system.validateText(this.text)) {
      throw new InvalidTextMessageException("Text of the message must contain between 1 and 120 characters!");
    }
    if (!system.validateMessage(this.title, this.recipient)) {
      throw new MissingArgumentException("Title and/or recipient can not be \"null\"!");
    }
    return SEND_MESSAGE;
  }
}
