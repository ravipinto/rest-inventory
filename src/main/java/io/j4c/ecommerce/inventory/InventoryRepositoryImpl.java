/*
 * Copyright (c) 2017 Oracle and/or its affiliates. All rights reserved.
 */
package io.j4c.ecommerce.inventory;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class InventoryRepositoryImpl implements InventoryRepository {

	@Override
	public List<ProductDO> findAll() {
		return ProductCache.getInstance().getAllProduct();
	}

	@Override
	public ProductDO findOne(Integer id) {
		return ProductCache.getInstance().getProduct(id);
	}

	@Override
	public void insert(ProductDO product) {
		ProductCache.getInstance().setProduct(product.getId(), product);
	}

}
