package com.clouway.util;

import com.clouway.core.Clock;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * Created by clouway on 7/25/14.
 */
public class SessionUtil {
  private final DB connection;

  public SessionUtil(DB connection) {

    this.connection = connection;
  }

  public void addUserSessionInDatabase(String session, String userId, Clock expirationTime) {
    BasicDBObject sessionRecord = new BasicDBObject("sid", session)
            .append("user_name", userId)
            .append("expiration_date", expirationTime.now());

    getCollection("sessions").insert(sessionRecord);


  }

  private DBCollection getCollection(String collectionName) {
    return connection.getCollection(collectionName);

  }
}
