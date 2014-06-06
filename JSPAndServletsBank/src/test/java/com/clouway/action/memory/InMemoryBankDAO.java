package com.clouway.action.memory;

import com.clouway.action.UserSessionsRepository;
import com.clouway.objects.Clock;
import com.clouway.objects.DepositAccountDAO;
import com.clouway.objects.Transaction;
import com.clouway.objects.TransactionHistory;
import com.clouway.objects.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clouway on 5/29/14.
 */
public class InMemoryBankDAO implements DepositAccountDAO, TransactionHistory {

  private final Clock clock;
  private final UserSessionsRepository userSession;
  private int currentAmount = 0;
  private List<Transaction> transactions = new ArrayList<Transaction>();

  public InMemoryBankDAO(Clock clock, UserSessionsRepository userSession) {

    this.clock = clock;
    this.userSession = userSession;
  }

  @Override
  public void deposit(int amount, String UUIDSession) {
    currentAmount += amount;

    createTransaction("deposit", amount, UUIDSession);

  }

  @Override
  public int getCurrentUserBankAmount() {
    return currentAmount;
  }

  @Override
  public int withdrawing(int withdrawingAmount, String UUIDSession) {
    if (currentAmount >=withdrawingAmount) {
      currentAmount -= withdrawingAmount;
      createTransaction("withdrawing", withdrawingAmount, UUIDSession);
    } else {
      return 0;
    }

    return withdrawingAmount;
  }

  @Override
  public List<Transaction> getUserHistory(int userID) {
    List<Transaction> list = new ArrayList<Transaction>();
    for(Transaction transaction : transactions) {
      if(transaction.getUserID() == userID) {
        list.add(transaction);
      }
    }
    return list;
  }

  @Override
  public void addTransaction(Transaction transaction) {
    transactions.add(transaction);
  }

  @Override
  public List<Transaction> getAllTransactions() {
    return this.transactions;
  }

  private void createTransaction(String transfer, int amount, String UUIDSession) {

    User user = userSession.findUserAssociatedWithSession(UUIDSession);

    addTransaction(new Transaction(transfer, amount, clock.now(), user.getUserID()));
  }
}
