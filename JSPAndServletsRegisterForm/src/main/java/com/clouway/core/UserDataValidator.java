package com.clouway.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;


@Singleton
public class UserDataValidator implements AuthorizationFormData {

  private final ErrorMessages errorMessages;
  private final RegexMessages regexMessages;

  @Inject
  public UserDataValidator(ErrorMessages errorMessages, RegexMessages regexMessages) {

    this.errorMessages = errorMessages;

    this.regexMessages = regexMessages;

  }

  @Override
  public String validateUserData(String parameterName, String parameterValue) {

    if (parameterValue.matches(regexMessages.getRegexForParameter(parameterName))) {
      return errorMessages.correct();
    }

    return errorMessages.wrong();
  }

}
