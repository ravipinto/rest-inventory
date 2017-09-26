/*
 * Copyright (c) 2017 Oracle and/or its affiliates. All rights reserved.
 */
package io.j4c.ecommerce.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Product entity.
 */
public class ProductDO {

	@JsonProperty(value="product-id")
    private Integer id;
    private Integer amount;

    /**
     * Initialize Product.
     */
    public ProductDO() {
    }

    /**
     * Initialize Product.
     *
     * @param id     product id
     * @param amount number of product instances
     */
    public ProductDO(Integer id, Integer amount) {
        this.id = id;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product{"
                + "id=" + id
                + ", amount=" + amount
                + '}';
    }
    
}
