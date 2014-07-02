package com.clouway.core;

import java.util.Map;

/**
 * Created by clouway on 6/26/14.
 */
public interface Validator {

  Map<String, UserMessage> validate(Map<String, String[]> parameters);
}
