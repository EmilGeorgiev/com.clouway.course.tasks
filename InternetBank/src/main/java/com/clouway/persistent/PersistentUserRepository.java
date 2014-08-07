package com.clouway.persistent;

import com.clouway.core.Clock;
import com.clouway.core.CurrentUser;
import com.clouway.core.RegistrationInfo;
import com.clouway.core.RegistrationMessages;
import com.clouway.core.SessionRepository;
import com.clouway.core.User;
import com.clouway.core.UserRepository;
import com.google.common.base.Optional;
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
 *
 */
@Singleton
public class PersistentUserRepository implements UserRepository, SessionRepository {

  private final Provider<DB> dbProvider;
  private final Clock clock;
  private final Provider<RegistrationMessages> messagesProvider;

  @Inject
  public PersistentUserRepository(Provider<DB> dbProvider,
                                  Clock clock,
                                  Provider<RegistrationMessages> messagesProvider) {

    this.dbProvider = dbProvider;
    this.clock = clock;
    this.messagesProvider = messagesProvider;
  }

  @Override
  public RegistrationInfo register(User user) {

    BasicDBObject query = new BasicDBObject("name", user.getName())
            .append("password", user.getPassword());

    BasicDBObject documentUpdate = new BasicDBObject("$set", new BasicDBObject("amount", 0.0));

    WriteResult writeResult = users().update(query, documentUpdate, true, false);

    if (writeResult.isUpdateOfExisting()) {
      return new RegistrationInfo(messagesProvider.get().failed());
    }

    return new RegistrationInfo(messagesProvider.get().success());

  }

  @Override
  public Optional<String> login(User user) {

    DBObject documentUser = new BasicDBObject("name", user.getName())
            .append("password", user.getPassword());

    DBObject doc = users().findOne(documentUser);

    if (doc == null) {
      return Optional.absent();
    }

    return  Optional.of(createUserSession(user.getName()));

  }

  @Override
  public Optional<CurrentUser> findBySession(String session) {

    BasicDBObject query = new BasicDBObject("sid", session);

    BasicDBObject doc = (BasicDBObject) sessions().findOne(query);

    String userName = doc.getString("user_name");

    return Optional.fromNullable(new CurrentUser(userName));
  }

  @Override
  public boolean authenticate(String sessionID) {

    BasicDBObject whereQuery = new BasicDBObject("sid", sessionID)
            .append("expiration_date", new BasicDBObject("$gt", clock.now()));

    DBObject user = sessions().findOne(whereQuery);

    if (user != null) {

      updateSession(whereQuery);

      return true;
    }

    return false;
  }

//  @Override
//  public void delete(String sid) {
//    BasicDBObject sessionDocument = new BasicDBObject("sid", sid);
//
//    sessions().remove(sessionDocument);
//  }

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
    return dbProvider.get().getCollection("users");
  }

  private DBCollection sessions() {
    return dbProvider.get().getCollection("sessions");
  }

}
