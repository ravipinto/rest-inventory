/*
 * Copyright (c) 2017 Oracle and/or its affiliates. All rights reserved.
 */

package io.j4c.ecommerce.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockoutDO {

	@JsonProperty(value="order-id")
	private String orderId;
    
	@JsonProperty(value="product-id")
	private Integer productId;
   	
	private Integer amount;

    public StockoutDO() {
    }

    public StockoutDO(String orderId, Integer productId, Integer amount) {
        this.orderId = orderId;
        this.productId = productId;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "StockoutTransaction{"
                + "orderId='" + orderId + '\''
                + ", productId=" + productId
                + ", amount=" + amount
                + '}';
    }
} 
