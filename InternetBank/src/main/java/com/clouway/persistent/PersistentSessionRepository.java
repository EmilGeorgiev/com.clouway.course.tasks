package com.clouway.persistent;

import com.clouway.core.Clock;
import com.clouway.core.SessionRepository;
import com.clouway.core.User;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import static com.clouway.core.DBMessages.EXPIRATION_DATE;
import static com.clouway.core.DBMessages.GREATE;
import static com.clouway.core.DBMessages.NAME;
import static com.clouway.core.DBMessages.SESSIONS;
import static com.clouway.core.DBMessages.SID;
import static com.clouway.core.DBMessages.USERS;

/**
 * Created by clouway on 7/21/14.
 */
@Singleton
public class PersistentSessionRepository implements SessionRepository{

  private final Provider<DB> connection;

  @Inject
  public PersistentSessionRepository(Provider<DB> connection, Clock clock) {
    this.connection = connection;
  }

  @Override
  public User authenticateSession(String sessionID, Clock date) {
    BasicDBObject whereQuery = new BasicDBObject(SID, sessionID)
                                         .append(EXPIRATION_DATE,
                                                 new BasicDBObject(GREATE, date.now()));


    DBObject user = users().findOne(whereQuery);

    if(user != null) {

      String userSession = (String) user.get(SID);

      String userName = (String) user.get(NAME);

      return new User(userSession, userName);
    }

    return null;

  }

  @Override
  public void deleteSessionByID(String sid) {
    BasicDBObject session = new BasicDBObject(SID, sid);

    sessions().remove(session);

  }

  private DBCollection users() {
    return connection.get().getCollection(USERS);
  }

  private DBCollection sessions() {
    return connection.get().getCollection(SESSIONS);
  }
}
