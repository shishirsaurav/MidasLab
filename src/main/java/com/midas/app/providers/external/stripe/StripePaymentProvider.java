package com.midas.app.providers.external.stripe;

import com.midas.app.enums.ProviderType;
import com.midas.app.models.Account;
import com.midas.app.providers.payment.CreateAccount;
import com.midas.app.providers.payment.PaymentProvider;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class StripePaymentProvider implements PaymentProvider {
  private final Logger logger = LoggerFactory.getLogger(StripePaymentProvider.class);

  private final StripeConfiguration configuration;

  /** providerName is the name of the payment provider */
  @Override
  public String providerName() {
    return "stripe";
  }

  /**
   * createAccount creates a new account in the payment provider.
   *
   * @param details is the details of the account to be created.
   * @return Account
   */
  @Override
  public Account createAccount(CreateAccount details) {
    try {
      Stripe.apiKey =
          "pk_test_51OgWl0SFQyweY2KJDkuovmWV8hWakvIIwrn4aOUynrTHJ69HJDqd72QpeVpVRm9JBbeWf9lJhEqslprjCNr0OrWb00JdsTXbs0";
      Map<String, Object> params = new HashMap<>();
      params.put("name", details.getFirstName());
      params.put("email", details.getEmail());
      Customer customer = Customer.create(params);
      details.setProviderId(customer.getId());
      details.setProviderType(ProviderType.STRIPE.getValue());
    } catch (StripeException e) {
      throw new RuntimeException(e);
    }

    throw new UnsupportedOperationException("Not implemented");
  }
}
