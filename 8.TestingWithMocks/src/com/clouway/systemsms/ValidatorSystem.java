package com.clouway.systemsms;

/**
 * Created by clouway on 12/27/13.
 */
public interface ValidatorSystem {

  boolean validateText(String text);

  boolean validateMessage(String title, String recipient);
}
