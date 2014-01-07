package com.clouway.systemsms;

/**
 * Created by clouway on 1/7/14.
 */
public class Sender {
  private SystemSms systemSms;
  private Validator validator;
  private final String SEND_MESSAGE = "Message is send";

  public Sender(SystemSms systemSms, Validator validator) {

    this.systemSms = systemSms;
    this.validator = validator;
  }

  public String sendMessage(Message message) {
    validator.validateText(message.getText());
    validator.validateTitle(message.getTitle());
    validator.validateRecipient(message.getRecipient());

    systemSms.send(message);
    return SEND_MESSAGE;
  }
}
