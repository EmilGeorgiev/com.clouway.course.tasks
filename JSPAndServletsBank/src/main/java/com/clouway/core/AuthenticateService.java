package com.clouway.core;

/**
 * Created by clouway on 6/13/14.
 */
public interface AuthenticateService {

  SessionID authenticate(String userName, String userPassword);
}
