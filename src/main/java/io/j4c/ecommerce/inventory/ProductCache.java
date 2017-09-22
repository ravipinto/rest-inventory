/*
 * Copyright (c) 2017 Oracle and/or its affiliates. All rights reserved.
 */
package io.j4c.ecommerce.inventory;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCache {

	/*
	 * Singleton
	 */
	private ProductCache() {
	}

	private static ProductCache INSTANCE = null;

	/*
	 * Quick solution to store sample data
	 */
	private HashMap<Integer, ProductDO> productData = new HashMap<Integer, ProductDO>();

	public static ProductCache getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ProductCache();
		}
		return INSTANCE;
	}

	public ProductDO getProduct(Integer id) {
		return productData.get(id);
	}

	public void setProduct(Integer id, ProductDO productDO) {
		productData.put(id, productDO);
	}

	public List<ProductDO> getAllProduct() {
		return productData.values().stream().collect(Collectors.toList());
	}

}
