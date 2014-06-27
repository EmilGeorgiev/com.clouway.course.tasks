package com.clouway.core;

import java.util.Date;

/**
 * Created by clouway on 5/26/14.
 */
public interface UserDAO {

  SessionID registerUserIfNotExist(String userName, String password, Date now);
}
