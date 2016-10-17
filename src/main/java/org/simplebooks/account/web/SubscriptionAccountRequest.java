package org.simplebooks.account.web;

public class SubscriptionAccountRequest {
  private String status;
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }

  private String parentAccountIdentifier;
  public String getParentAccountIdentifier() {
    return parentAccountIdentifier;
  }
  public void setParentAccountIdentifier(String parentAccountIdentifier) {
    this.parentAccountIdentifier = parentAccountIdentifier;
  }

  private String editionCode;
  public String getEditionCode() {
    return editionCode;
  }
  public void setEditionCode(String editionCode) {
    this.editionCode = editionCode;
  }

  private String quantity;
  public String getQuantity() {
      return quantity;
  }
  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  private String unit;
  public String getUnit() {
      return unit;
  }
  public void setUnit(String unit) {
    this.unit = unit;
  }
}
