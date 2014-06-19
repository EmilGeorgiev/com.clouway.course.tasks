package com.clouway.core;

import java.util.Date;

/**
 * Created by clouway on 5/26/14.
 */
public interface UserDAO {

  SessionID register(String userName, String userPassword, Date now);

  User findUser(String userName, String userPassword);
}
