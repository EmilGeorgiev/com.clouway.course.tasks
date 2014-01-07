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
  public void validateText(String text) {
    if (text.length() > textSize) {
      throw new IllegalArgumentException("Message must contain less than " + textSize + " characters");
    }
  }

  @Override
  public void validateTitle(String title) {
    if (title == null) {
      throw new IllegalArgumentException("You need to add a title!");
    }
  }

  @Override
  public void validateRecipient(String recipient) {
    if (recipient == null) {
      throw new IllegalArgumentException("You need to add an recipient!");
    }
  }
}
