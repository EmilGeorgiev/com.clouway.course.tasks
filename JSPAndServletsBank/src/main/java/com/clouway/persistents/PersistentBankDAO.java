package com.clouway.persistents;

import com.clouway.core.AccountBankDAO;
import com.clouway.core.Clock;
import com.clouway.core.CurrentAmountRepository;
import com.clouway.core.CurrentUser;
import com.clouway.core.DepositListener;
import com.clouway.core.Transaction;
import com.clouway.core.TransactionHistory;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by clouway on 6/6/14.
 */
@Singleton
public class PersistentBankDAO implements AccountBankDAO, TransactionHistory, CurrentAmountRepository, DepositListener{

  private final Provider<Connection> connectionProvider;
  private final Provider<CurrentUser> currentUser;
  private final DepositListener depositListener;
  private final Clock clock;

  @Inject
  public PersistentBankDAO(Provider<Connection> connectionProvider,
                           Provider<CurrentUser> currentUser,
                           DepositListener depositListener,
                           Clock clock) {

    this.connectionProvider = connectionProvider;
    this.currentUser = currentUser;
    this.depositListener = depositListener;
    this.clock = clock;
  }

  @Override
  public void deposit(float amount, int userID) {
    PreparedStatement updateAmount = null;

    Connection connection = connectionProvider.get();
    try {

      updateAmount = connection.prepareStatement("UPDATE Accounts SET amount = amount + ? WHERE user_id = ?");


      updateAmount.setFloat(1, amount);
      updateAmount.setInt(2, userID);

      updateAmount.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (updateAmount != null) {
          updateAmount.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    onTransaction(amount, userID, "deposit");
  }

  @Override
  public float withdrawing(float withdrawingAmount, int userID) {
    PreparedStatement preparedStatement = null;

    Connection connection = connectionProvider.get();

    try {
      preparedStatement = connection.prepareStatement("UPDATE Accounts SET amount = amount - ? WHERE user_id = ?");

      preparedStatement.setFloat(1, withdrawingAmount);
      preparedStatement.setInt(2, userID);

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return withdrawingAmount;
  }

  @Override
  public float getCurrentUserBankAmount(int userID) {

    PreparedStatement preparedStatement = null;
    Connection connection = connectionProvider.get();

    try {
      preparedStatement = connection.prepareStatement("SELECT amount FROM Accounts " +
              "WHERE user_id = ?");

      preparedStatement.setInt(1, userID);

      ResultSet resultSet = preparedStatement.executeQuery();

      resultSet.next();

      return resultSet.getInt("amount");

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

    return 0;
  }

  @Override
  public List<Transaction> getUserHistory(int userID) {
    PreparedStatement preparedStatement = null;

    Connection connection = connectionProvider.get();

    try {
      preparedStatement = connection.prepareStatement("SELECT tranfer, date, amount, user_id FROM TransferHistory " +
              "WHERE user_id = ?");

      preparedStatement.setInt(1, userID);

      ResultSet resultSet = preparedStatement.executeQuery();

      List<Transaction> transactions = new ArrayList<Transaction>();

      while (resultSet.next()) {
        String transfer = resultSet.getString("tranfer");
        Timestamp date = resultSet.getTimestamp("date");
        float amount = resultSet.getFloat("amount");
        int id = resultSet.getInt("user_id");

        transactions.add(new Transaction(transfer, amount, date, id));
      }

      return transactions;
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
  public List<Transaction> getAllTransactions() {

    PreparedStatement preparedStatement = null;

    Connection connection = connectionProvider.get();

    try {
      preparedStatement = connection.prepareStatement("SELECT tranfer, date, amount, user_id FROM TransferHistory");

      ResultSet resultSet = preparedStatement.executeQuery();

      List<Transaction> transactions = new ArrayList<Transaction>();
      while (resultSet.next()) {
        String transfer = resultSet.getString("tranfer");
        Timestamp date = resultSet.getTimestamp("date");
        float amount = resultSet.getFloat("amount");
        int id = resultSet.getInt("user_id");

        transactions.add(new Transaction(transfer, amount, date, id));
      }

      return transactions;
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public void onTransaction(float amount, int userID, String transaction) {
    PreparedStatement prepareStatement = null;

   Connection connection = connectionProvider.get();

    try {
      prepareStatement = connection.prepareStatement("INSERT INTO TransferHistory (tranfer, date, amount, user_id) " +
              "VALUES (?, ?, ?, ?) ");

      prepareStatement.setString(1, transaction);

      prepareStatement.setTimestamp(2, clock.now());

      prepareStatement.setFloat(3, amount);

      prepareStatement.setInt(4, userID);

      prepareStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (prepareStatement != null) {
          prepareStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
