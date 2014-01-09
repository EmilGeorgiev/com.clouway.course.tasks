package com.clouway.systemsms;

/**
 * Created by clouway on 1/7/14.
 */
public class MessageValidator implements Validator {

  private final int textSize;

  public MessageValidator(int textSize) {

    this.textSize = textSize;
  }

  @Override
  public void validateMessage(Message message) {
    if (message.getTitle() == null) {
      throw new IllegalArgumentException("You need to add a title!");
    }
    if (message.getText().length() > textSize) {
      throw new IllegalArgumentException("Message must contain less than " + textSize + " characters");
    }
    if (message.getRecipient() == null) {
      throw new IllegalArgumentException("You need to add an recipient!");
    }
  }
}
