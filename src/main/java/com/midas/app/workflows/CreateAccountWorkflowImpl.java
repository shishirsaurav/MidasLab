package com.midas.app.workflows;

import com.midas.app.activities.AccountActivity;
import com.midas.app.models.Account;
import io.temporal.workflow.Workflow;

public class CreateAccountWorkflowImpl implements CreateAccountWorkflow {

  private final AccountActivity activity = Workflow.newActivityStub(AccountActivity.class);

  @Override
  public Account createAccount(Account details) {
    return activity.createPaymentAccount(details);
  }
}
