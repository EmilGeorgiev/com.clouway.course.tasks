package com.clouway.http;

import com.clouway.core.BankAccountMessages;
import com.clouway.core.CurrentAmountRepository;
import com.clouway.core.CurrentUser;
import com.clouway.core.SiteMap;
import com.clouway.core.User;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by clouway on 5/29/14.
 */
@Singleton
public class ViewCurrentAmountServlet extends HttpServlet{


  private final CurrentAmountRepository currentAmountRepository;
  private final SiteMap siteMap;
  private final Provider<CurrentUser> currentUserProvider;
  private final BankAccountMessages bankAccountMessages;

  @Inject
  public ViewCurrentAmountServlet(CurrentAmountRepository currentAmountRepository,
                                  SiteMap siteMap,
                                  Provider<CurrentUser> currentUserProvider,
                                  BankAccountMessages bankAccountMessages) {

    this.currentAmountRepository = currentAmountRepository;
    this.siteMap = siteMap;
    this.currentUserProvider = currentUserProvider;
    this.bankAccountMessages = bankAccountMessages;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    User user = currentUserProvider.get().getUser();

    String currentAmount = String.valueOf(currentAmountRepository.getCurrentUserBankAmount(user.getUserID()));

    req.setAttribute(bankAccountMessages.currentAmount(), currentAmount);

     RequestDispatcher requestDispatcher = req.getRequestDispatcher(siteMap.viewAmountPage());

    requestDispatcher.forward(req, resp);
  }
}
