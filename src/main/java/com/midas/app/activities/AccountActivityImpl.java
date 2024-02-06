package com.midas.app.activities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.midas.app.models.Account;
import com.midas.app.providers.payment.CreateAccount;
import com.midas.app.providers.payment.PaymentProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountActivityImpl implements AccountActivity {

  private final PaymentProvider paymentProvider;

  @Override
  public Account saveAccount(Account account) {
    return null;
  }

  @Override
  public Account createPaymentAccount(Account account) {
    return paymentProvider.createAccount(
        new ObjectMapper().convertValue(account, CreateAccount.class));
  }
}
