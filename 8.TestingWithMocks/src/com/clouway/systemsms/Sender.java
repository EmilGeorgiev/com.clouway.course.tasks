package com.clouway.systemsms;

/**
 * Created by clouway on 1/7/14.
 */
public class Sender {
  private SmsSystem smsSystem;
  private Validator validator;
  private final String sendMessage;

  public Sender(SmsSystem smsSystem, Validator validator, String sendMessage) {

    this.smsSystem = smsSystem;
    this.validator = validator;
    this.sendMessage = sendMessage;
  }

  public String sendMessage(Message message) {
    validator.validateMessage(message);
    smsSystem.send(message);
    return sendMessage;
  }

  public String getInfoForSend() {
    return sendMessage;
  }
}
