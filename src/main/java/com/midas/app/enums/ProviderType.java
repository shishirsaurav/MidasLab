package com.midas.app.enums;

public enum ProviderType {
  STRIPE("Stripe");
  private String value;

  private ProviderType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
