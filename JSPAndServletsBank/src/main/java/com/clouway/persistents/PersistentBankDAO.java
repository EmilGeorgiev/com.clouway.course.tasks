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
  public int withdrawing(float withdrawingAmount, int userID) {
    return 0;
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

      int currentAmount = 0;

      while (resultSet.next()) {
        int amount = resultSet.getInt("amount");

        currentAmount += amount;
      }

      return currentAmount;

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
    return null;
  }

  @Override
  public void addTransaction(Transaction transaction) {

  }

  @Override
  public List<Transaction> getAllTransactions() {
    List<Transaction> transactions = new ArrayList<Transaction>();

    transactions.add(new Transaction("deposit", 100, clock.now().toString(), 1));
    transactions.add(new Transaction("deposit", 150, clock.now().toString(), 1));

    return transactions;
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
