package com.clouway.persistents;

import com.clouway.core.AuthenticateService;
import com.clouway.core.Clock;
import com.clouway.core.SessionID;
import com.clouway.core.User;
import com.clouway.core.UserDAO;
import com.clouway.core.UserSessionsRepository;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

@Singleton
public class PersistentUserDAO implements UserDAO, UserSessionsRepository, AuthenticateService {


  private final Provider<Connection> connectionProvider;
  private final Clock clock;

  @Inject
  public PersistentUserDAO(Provider<Connection> connectionProvider, Clock clock) {

    this.connectionProvider = connectionProvider;
    this.clock = clock;
  }

  @Override
  public SessionID authenticate(String userName, String userPassword) {

    SessionID sessionID = null;

    PreparedStatement preparedStatement = null;
    Connection connection = connectionProvider.get();

    try {
      preparedStatement = connection.prepareStatement("SELECT user_id, name FROM Users " +
              "WHERE name = ? AND password = ?");

      preparedStatement.setString(1, userName);
      preparedStatement.setString(2, userPassword);

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {

        int id = resultSet.getInt("user_id");

        sessionID = createSessionID(userName, id);
      }

      return sessionID;

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return sessionID;

  }


  @Override
  public void register(String userName, String userPassword) {
    PreparedStatement preparedStatement = null;
    Connection connection = connectionProvider.get();

    try {
      preparedStatement = connection.prepareStatement("INSERT INTO Users (name, password) " +
              "VALUES (?, ?)");

      preparedStatement.setString(1, userName);
      preparedStatement.setString(2, userPassword);

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    createAccount(userName, userPassword);
  }



  @Override
  public User isUserExistBySession(String sessionId) {

    PreparedStatement preparedStatement = null;

    Connection connection = connectionProvider.get();

    try {
      preparedStatement = connection.prepareStatement("SELECT expiry_date, user_id FROM Session WHERE sid = ?");

      preparedStatement.setString(1, sessionId);

      ResultSet resultSet = preparedStatement.executeQuery();

      int userID = 0;
       if (resultSet.next()) {
         Timestamp session = resultSet.getTimestamp("expiry_date");
         userID = resultSet.getInt("user_id");

         if (clock.now().compareTo(session) > 0) {
           deleteSession(sessionId);
           return null;
         }

       }

      return findUserByID(userID);

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }



  @Override
  public void deleteSession(String sessionID) {
    PreparedStatement preparedStatement = null;

    Connection connection = connectionProvider.get();

    try {

      preparedStatement = connection.prepareStatement("DELETE FROM Session WHERE sid = ?");

      preparedStatement.setString(1, sessionID);

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public boolean isValidUserSession(String sessionID) {

    if(sessionID == null) {
      cleanExpiredSessionsID();
      return false;
    }

    PreparedStatement preparedStatement = null;

    Connection connection = connectionProvider.get();

    try {
      preparedStatement = connection.prepareStatement("SELECT expiry_date FROM Session WHERE sid = ?");

      preparedStatement.setString(1, sessionID);

      ResultSet resultSet = preparedStatement.executeQuery();

      resultSet.next();

      Timestamp expiryDate = resultSet.getTimestamp("expiry_date");

      if (clock.now().compareTo(expiryDate) < 0) {
        updateUserSession(sessionID);

        cleanExpiredSessionsID();
        return true;
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return false;
  }



//  @Override
//  public String getUserSessionIDByID(int userID) {
//    PreparedStatement preparedStatement = null;
//
//    Connection connection = connectionProvider.get();
//
//    try {
//      preparedStatement = connection.prepareStatement("SELECT sid FROM Session WHERE user_id = ?");
//
//      preparedStatement.setInt(1, userID);
//
//      ResultSet resultSet = preparedStatement.executeQuery();
//
//      resultSet.next();
//
//      return resultSet.getString("sid");
//
//    } catch (SQLException e) {
//      e.printStackTrace();
//    } finally {
//      try {
//        if (preparedStatement != null) {
//          preparedStatement.close();
//        }
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
//    }
//    return null;
//  }

  private SessionID createSessionID(String userName, int userID) {
    HashFunction sha1 = Hashing.sha1();

    String sessionID = sha1.hashString(userName + clock.now()).toString();

    long expiredTime = setTimeExpired();

    PreparedStatement preparedStatement = null;

    Connection connection = connectionProvider.get();

    try {
      preparedStatement = connection.prepareStatement("INSERT INTO Session (sid, user_id, expiry_date) " +
              "VALUES (?, ?, ?)");

      preparedStatement.setString(1, sessionID);
      preparedStatement.setInt(2, userID);
      preparedStatement.setTimestamp(3,  new Timestamp(expiredTime));

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return new SessionID(sessionID);
  }

  private void createAccount(String userName, String userPassword) {
    PreparedStatement preparedStatement = null;

    Connection connection = connectionProvider.get();

      try {
        preparedStatement = connection.prepareStatement("SELECT user_id FROM Users " +
                "WHERE name = ? AND password = ?");

        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, userPassword);

        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        int userID = resultSet.getInt("user_id");


        preparedStatement = connection.prepareStatement("INSERT INTO Accounts (amount, user_id) " +
                "VALUES (?, ?)");

        preparedStatement.setInt(1, 0);
        preparedStatement.setInt(2, userID);

        preparedStatement.executeUpdate();

        createSessionID(userName, userID);

      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        try {
          if (preparedStatement != null) {
            preparedStatement.close();
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }


  }

  private User findUserByID(int userID) {
    PreparedStatement preparedStatement = null;

    Connection connection = connectionProvider.get();

    try {
      preparedStatement = connection.prepareStatement("SELECT name, password FROM Users WHERE user_id = ?");

      preparedStatement.setInt(1, userID);

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        String name = resultSet.getString("name");
        String password = resultSet.getString("password");

        return new User(name, password, userID);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  private long setTimeExpired() {

    Calendar calendar = Calendar.getInstance();

    calendar.setTime(clock.now());

    calendar.add(Calendar.MINUTE, clock.getExpiryTime());

    return calendar.getTimeInMillis();


  }

  private void updateUserSession(String sessionID) {

    long expiredTime = setTimeExpired();

    PreparedStatement preparedStatement = null;

    Connection connection = connectionProvider.get();

    try {
      preparedStatement = connection.prepareStatement("UPDATE Session SET expiry_date = ? WHERE sid = ?");

      preparedStatement.setTimestamp(1, new Timestamp(expiredTime));
      preparedStatement.setString(2, sessionID);

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

  }

  private void cleanExpiredSessionsID() {
    PreparedStatement preparedStatement = null;

    Connection connection = connectionProvider.get();

    try {
      preparedStatement = connection.prepareStatement("DELETE Session WHERE expiry_date < ?");

      preparedStatement.setTimestamp(1, clock.now());

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

}
