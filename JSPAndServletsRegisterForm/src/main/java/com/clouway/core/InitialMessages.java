package com.clouway.core;

import java.util.Map;

/**
 * Created by clouway on 6/27/14.
 */
public interface InitialMessages {
  Map<String,String> getMessages();

  String value();

  String emptyValue();

  String getMessageForField(String parameterName);
}
