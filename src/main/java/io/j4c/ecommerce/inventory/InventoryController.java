/*
 * Copyright (c) 2017 Oracle and/or its affiliates. All rights reserved.
 */
package io.j4c.ecommerce.inventory;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InventoryController {

	@Autowired
	private InventoryRepository inventoryRepository;

	@RequestMapping(value = "/inventory", method = RequestMethod.GET)
	public ResponseEntity<Collection<ProductDO>> products() {

		ResponseEntity<Collection<ProductDO>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);

		List<ProductDO> products = (List<ProductDO>) inventoryRepository.findAll();

		if (products.size() > 0) {
			response = new ResponseEntity<Collection<ProductDO>>(products, HttpStatus.OK);
		}

		return response;
	}

	@RequestMapping(value = "/inventory/{productId}", method = RequestMethod.GET)
	public ResponseEntity<ProductDO> product(@PathVariable(value = "productId") Integer id) {

		ResponseEntity<ProductDO> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);

		ProductDO product = inventoryRepository.findOne(id);

		if (product != null) {
			response = new ResponseEntity<ProductDO>(product, HttpStatus.OK);
		}

		return response;
	}

	@RequestMapping(value = "/inventory/stockout", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, String>> stockOut(@RequestBody StockoutDO stockout) {

		ResponseEntity<HashMap<String, String>> response = null;

		ProductDO product = inventoryRepository.findOne(stockout.getProductId());

		/*
		 * default stockout value is 1 if not defined
		 */
		if (stockout != null && stockout.getAmount() == null) {
			stockout.setAmount(new Integer(1));
		}

		if (stockout == null || stockout.getOrderId() == null || stockout.getOrderId().length() == 0
				|| stockout.getProductId() == null || stockout.getProductId().intValue() < 0) {
			response = new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		} else if (product == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else if (product.getAmount() < stockout.getAmount()) {
			HashMap<String, String> result = new HashMap<String, String>();
			result.put("error", "Out of stock.");
			response = new ResponseEntity<HashMap<String, String>>(result, HttpStatus.BAD_REQUEST);
		} else {
			HashMap<String, String> result = new HashMap<String, String>();
			product.setAmount(product.getAmount() - stockout.getAmount());
			result.put("remaining", product.getAmount().toString());
			response = new ResponseEntity<HashMap<String, String>>(result, HttpStatus.OK);
		}

		return response;
	}
	
	@RequestMapping(value = "/inventory", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, String>> insertProduct(@RequestBody ProductDO product) {

		ResponseEntity<HashMap<String, String>> response = null;
		HashMap<String, String> result = new HashMap<String, String>();

		try {
			ProductDO existingProduct = inventoryRepository.findOne(product.getId());
			
			if (existingProduct == null) {
				inventoryRepository.insert(product);
			} else {
				existingProduct.setAmount(existingProduct.getAmount() + product.getAmount());
			}

			result.put("amount", existingProduct.getAmount().toString());
			response = new ResponseEntity<HashMap<String,String>>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.put("error", e.getMessage());
			response = new ResponseEntity<HashMap<String,String>>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}
}
