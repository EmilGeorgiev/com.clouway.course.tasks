package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.SiteMap;
import com.clouway.core.TransactionInfo;
import com.clouway.core.TransactionRepository;
import com.google.inject.Inject;
import com.google.sitebricks.At;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Service;
import com.google.sitebricks.http.Post;

/**
 * Created by HOME on 3.8.2014 г..
 */
@At("/withdrawController")
@Service
public class WithdrawController {

    private final BankRepository bankRepository;
    private final SiteMap siteMap;

    private Double amount;

    @Inject
    public WithdrawController(BankRepository bankRepository,
                              SiteMap siteMap) {

        this.bankRepository = bankRepository;
        this.siteMap = siteMap;
    }

    @Post
    public Reply<?> withdraw() {
        TransactionInfo info = bankRepository.withdraw(amount);

        return Reply.saying().redirect(siteMap.mainController()+ "?isShowMessage=true&userMessage="+info.getMessage());
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}