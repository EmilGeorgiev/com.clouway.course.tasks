package com.clouway.core;

/**
 * Created by clouway on 6/26/14.
 */
public interface AuthorizationFormData {


  String getRegexForParameter(String parameterName);

  boolean isUserDataMatchToRegex(String parameter, String regex);
}
