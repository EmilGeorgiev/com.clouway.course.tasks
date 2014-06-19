package com.clouway.core;

import java.util.Date;

/**
 * Created by clouway on 6/13/14.
 */
public interface AuthenticateService {

  SessionID authenticate(String userName, String userPassword, Date now);

}
