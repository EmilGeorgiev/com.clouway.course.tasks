package com.clouway.persistent;

import com.clouway.core.Clock;
import com.clouway.core.SessionRepository;
import com.clouway.core.User;
import com.clouway.core.UserEntity;
import com.clouway.core.UserMessage;
import com.clouway.core.UserRepository;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by clouway on 7/17/14.
 */
@Singleton
public class PersistentUserRepository implements UserRepository, SessionRepository {

  private final Provider<DB> connection;
  private final Clock clock;
  private final Provider<UserMessage> userMessage;

  @Inject
  public PersistentUserRepository(Provider<DB> connection,
                                  Clock clock,
                                  Provider<UserMessage> userMessage) {

    this.connection = connection;
    this.clock = clock;
    this.userMessage = userMessage;
  }

  @Override
  public String registerUserIfNotExist(UserEntity user) {

    BasicDBObject query = new BasicDBObject("name", user.getName())
                                    .append("password", user.getPassword());

    BasicDBObject documentUpdate = new BasicDBObject("$set", new BasicDBObject("account", 0.0));

    WriteResult writeResult = users().update(query, documentUpdate, true, false);

    if (writeResult.isUpdateOfExisting()) {
      return userMessage.get().failed();
    }

    return userMessage.get().success();

  }

  @Override
  public String isExist(UserEntity user) {

    DBObject documentUser = new BasicDBObject("name", user.getName())
            .append("password", user.getPassword());

    DBObject doc = users().findOne(documentUser);

    if (doc == null) {
      return null;
    }

    return createUserSession(user.getName());

  }

  @Override
  public User findBySessionID(String session) {
    BasicDBObject documentQuery = new BasicDBObject("sid", session);

    String userName = (String) sessions().findOne(documentQuery).get("user_name");

    documentQuery = new BasicDBObject("name", userName);

    DBObject user = users().findOne(documentQuery);

    String userSession = (String) user.get("session");

    return new User(userSession, userName);
  }

  @Override
  public User authenticateSession(String sessionID, Clock date) {
    BasicDBObject whereQuery = new BasicDBObject("sid", sessionID)
                                         .append("expiration_date", new BasicDBObject("$gt", date.now()));

    DBObject user = sessions().findOne(whereQuery);

    if(user != null) {

      updateSession(whereQuery);

      String userSession = (String) user.get("sid");

      String userName = (String) user.get("name");

      return new User(userSession, userName);
    }

    return null;
  }

  @Override
  public void deleteSessionByID(String sid) {
    BasicDBObject sessionDocument = new BasicDBObject("sid", sid);

    sessions().remove(sessionDocument);
  }

  private void updateSession(BasicDBObject whereQuery) {

    BasicDBObject updateDocument = new BasicDBObject("expiration_date", getDateExpired(clock.now()));

    sessions().update(whereQuery, updateDocument);
  }

  private String createUserSession(String userName) {

    HashFunction sha1 = Hashing.sha1();

    String sessionID = sha1.hashString(userName + clock.now()).toString();

    Date expiredDate = getDateExpired(clock.now());

    BasicDBObject documentSession = new BasicDBObject("sid", sessionID)
            .append("user_name", userName)
            .append("expiration_date", expiredDate);

    sessions().insert(documentSession);

    return sessionID;

  }

  private Date getDateExpired(Date date) {

    Calendar calendar = Calendar.getInstance();

    calendar.setTime(date);

    calendar.add(Calendar.MINUTE, 30);

    return calendar.getTime();

  }

  private DBCollection users() {
    return connection.get().getCollection("users");
  }

  private DBCollection sessions() {
    return connection.get().getCollection("sessions");
  }

}
