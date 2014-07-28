package com.clouway.persistent;

import com.clouway.core.Clock;
import com.clouway.core.SessionRepository;
import com.clouway.core.User;
import com.clouway.core.UserDTO;
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

import static com.clouway.core.DBMessages.*;

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
  public String registerUserIfNotExist(UserDTO user) {

    BasicDBObject documentQuery = new BasicDBObject(NAME, user.getName());

    BasicDBObject documentUpdate = new BasicDBObject("$set", new BasicDBObject(ACCOUNT, 0));

    WriteResult writeResult = users().update(documentQuery, documentUpdate, true, false);

    if (writeResult.isUpdateOfExisting()) {
      return userMessage.get().failed();
    }

    return userMessage.get().success();

  }

  @Override
  public String isUserExist(UserDTO user) {

    DBObject documentUser = new BasicDBObject(NAME, user.getName())
            .append(PASSWORD, user.getPassword());

    DBObject doc = users().findOne(documentUser);

    if (documentUser == null) {
      return null;
    }

    return createUserSession(user.getName());

  }

  @Override
  public User findUserBySessionID(String session) {
    BasicDBObject documentQuery = new BasicDBObject(SID, session);

    String userName = (String) sessions().findOne(documentQuery).get(USER_NAME);

    documentQuery = new BasicDBObject(NAME, userName);

    DBObject user = users().findOne(documentQuery);

    String userSession = (String) user.get(SESSION);

    return new User(userSession, userName);
  }

  @Override
  public User authenticateSession(String sessionID, Clock date) {
    BasicDBObject whereQuery = new BasicDBObject(SID, sessionID)
                                         .append(EXPIRATION_DATE, new BasicDBObject(GREATE, date.now()));

    DBObject user = users().findOne(whereQuery);

    if(user != null) {

      updateSession(whereQuery);

      String userSession = (String) user.get(SID);

      String userName = (String) user.get(NAME);

      return new User(userSession, userName);
    }

    return null;
  }

  @Override
  public void deleteSessionByID(String sid) {
    BasicDBObject sessionDocument = new BasicDBObject(SID, sid);

    sessions().remove(sessionDocument);
  }

  private void updateSession(BasicDBObject whereQuery) {

    BasicDBObject updateDocument = new BasicDBObject(EXPIRATION_DATE, getDateExpired(clock.now()));

    sessions().update(whereQuery, updateDocument);
  }

  private String createUserSession(String userName) {

    HashFunction sha1 = Hashing.sha1();

    String sessionID = sha1.hashString(userName + clock.now()).toString();

    Date expiredDate = getDateExpired(clock.now());

    BasicDBObject documentSession = new BasicDBObject("sid", sessionID)
            .append(USER_NAME, userName)
            .append(EXPIRATION_DATE, expiredDate);

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
    return connection.get().getCollection(USERS);
  }

  private DBCollection sessions() {
    return connection.get().getCollection(SESSIONS);
  }

}
