package com.ibm.microservices.ReceiveOrderService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_details")
public class ReceiveOrder {
	
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long orderId;
  
  private String orderedItemName;
  
  @Column(length = 5000)
  private String userToken;
  
  private String serviceToken;
  
  private String transactionToken;

public long getOrderId() {
	return orderId;
}

public void setOrderId(long orderId) {
	this.orderId = orderId;
}

public String getOrderedItemName() {
	return orderedItemName;
}

public void setOrderedItemName(String orderedItemName) {
	this.orderedItemName = orderedItemName;
}

public String getUserToken() {
	return userToken;
}

public void setUserToken(String userToken) {
	this.userToken = userToken;
}

public String getServiceToken() {
	return serviceToken;
}

public void setServiceToken(String serviceToken) {
	this.serviceToken = serviceToken;
}

public String getTransactionToken() {
	return transactionToken;
}

public void setTransactionToken(String transactionToken) {
	this.transactionToken = transactionToken;
}
  
}
