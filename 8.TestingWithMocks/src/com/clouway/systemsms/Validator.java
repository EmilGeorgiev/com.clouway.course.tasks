package com.clouway.systemsms;

/**
 * Created by clouway on 12/27/13.
 */
public interface Validator {

  void validateText(String text);

  void validateTitle(String title);

  void validateRecipient(String recipient);
}
