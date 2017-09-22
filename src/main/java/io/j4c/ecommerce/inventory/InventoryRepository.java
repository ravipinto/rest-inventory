/*
 * Copyright (c) 2017 Oracle and/or its affiliates. All rights reserved.
 */
package io.j4c.ecommerce.inventory;

import java.util.List;

public interface InventoryRepository {

	/*
	 * Get all products
	 */
	List<ProductDO> findAll();

	/*
	 * Get productb by id
	 */
	ProductDO findOne(Integer id);

	/*
	 * Insert a product
	 */
	void insert(ProductDO product);
}
