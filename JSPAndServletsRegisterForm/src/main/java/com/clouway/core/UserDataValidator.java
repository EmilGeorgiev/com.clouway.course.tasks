package com.clouway.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;


@Singleton
public class UserDataValidator implements Validator {

  private final AuthenticateMessages authenticateMessages;
  private final ValidatorMessages validatorMessages;
  private final InitialMessages initialMessages;
  private final SiteMap siteMap;
  private Map<String, UserMessage> userMessages = new HashMap<String, UserMessage>();

  @Inject
  public UserDataValidator(AuthenticateMessages authenticateMessages,
                           ValidatorMessages validatorMessages,
                           InitialMessages initialMessages,
                           SiteMap siteMap) {

    this.authenticateMessages = authenticateMessages;

    this.validatorMessages = validatorMessages;

    this.initialMessages = initialMessages;
    this.siteMap = siteMap;
  }

  @Override
  public Map<String, UserMessage> validate(Map<String, String[]> parameters) {

    if(parameters.isEmpty()) {
      return initializeUserMessages();
    }

    for(Map.Entry<String, String[]> entry : parameters.entrySet()) {

      String parameterValue = entry.getValue()[0];

      String parameterName = entry.getKey();

      matchParameter(parameterName, parameterValue);

    }

    return userMessages;
  }

  /**
   * Check whether parameter value match on regex and add new value in <code>UserMessages<code>
   * @param parameterName name of the parameter.
   * @param parameterValue value of the parameter.
   */
  private void matchParameter(String parameterName, String parameterValue) {
    if (parameterValue.matches(validatorMessages.getValidatorMessageForParameter(parameterName))) {

      userMessages.put(parameterName, new UserMessage(authenticateMessages.correct(), parameterValue));

    } else {

      userMessages.put(parameterName, new UserMessage(authenticateMessages.wrong(), parameterValue));

    }
  }

  /**
   * Fill in initial value and message on field.
   * @return <code>Map</code> with all initial messages
   */
  private Map<String, UserMessage> initializeUserMessages() {
    Map<String, UserMessage> messageMap = new HashMap<String, UserMessage>();

    messageMap.put(siteMap.firstName(), new UserMessage(initialMessages.getMessageForField(siteMap.firstName()), siteMap.emptyValue()));
    messageMap.put(siteMap.lastName(), new UserMessage(initialMessages.getMessageForField(siteMap.lastName()), siteMap.emptyValue()));
    messageMap.put(siteMap.egn(), new UserMessage(initialMessages.getMessageForField(siteMap.egn()), siteMap.emptyValue()));
    messageMap.put(siteMap.age(), new UserMessage(initialMessages.getMessageForField(siteMap.age()), siteMap.emptyValue()));
    messageMap.put(siteMap.address(), new UserMessage(initialMessages.getMessageForField(siteMap.address()), siteMap.emptyValue()));
    messageMap.put(siteMap.password(), new UserMessage(initialMessages.getMessageForField(siteMap.password()), siteMap.emptyValue()));

    return messageMap;
  }

}
