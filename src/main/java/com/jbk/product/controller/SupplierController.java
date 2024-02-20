package com.jbk.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.product.entity.Supplier;
import com.jbk.product.service.SupplierService;

@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {

	@Autowired
	private SupplierService service;

	@PostMapping(value = "/save-supplier")
	public ResponseEntity<Boolean> saveSupplier(@RequestBody Supplier supplier) {

		boolean isAdded = service.saveSupplier(supplier);
		if (isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get-supplier-by-id/{id}")
	public ResponseEntity<Supplier> getSupplierById(@PathVariable long id) {
		Supplier supplier = service.getSupplierById(id);
		if (supplier != null) {
			return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "/get-supplier-by-name/{supplierName}")
	public ResponseEntity<Supplier> getSupplierByName(@PathVariable String supplierName) {

		Supplier supplier = service.getSupplierByName(supplierName);

		if (supplier != null) {

			return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
		} else {
			return new ResponseEntity<Supplier>(HttpStatus.NO_CONTENT);
		}

	}

}
