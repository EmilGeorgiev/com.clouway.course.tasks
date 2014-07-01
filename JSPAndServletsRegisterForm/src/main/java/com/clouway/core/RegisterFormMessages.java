package com.clouway.core;

import java.util.Map;

/**
 * Created by clouway on 6/27/14.
 */
public interface RegisterFormMessages {
  Map<String,String> getMessages();

  String value();

  String emptyValue();
}
