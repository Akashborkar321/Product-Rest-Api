package com.jbk.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.product.entity.Product;
import com.jbk.product.model.FinalProduct;
import com.jbk.product.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping(value = "/save-product")
	public ResponseEntity<Boolean> saveProduct(@RequestBody Product product) {
		boolean isAdded = service.saveProduct(product);
		if (isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get-product-by-id/{id}")

	public ResponseEntity<Product> getProductById(@PathVariable long id) {
		Product product = service.getProductById(id);
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/get-all-products")
	public ResponseEntity<List<Product>> getAllProducts() {

		List<Product> productList = service.getAllProducts();
		if (productList != null) {
			return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping(value = "/delete/{productId}")
	public ResponseEntity<Boolean> deleteProductById(@PathVariable long productId) {
		boolean isDeleted = service.deleteProductById(productId);
		if (isDeleted) {
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@PutMapping(value = "/update")
	public ResponseEntity<Boolean> updateProduct(@RequestBody Product product) {
		boolean isUpdated = service.updateProduct(product);
		if (isUpdated) {
			return new ResponseEntity<Boolean>(isUpdated, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "/get-product-by-id_ASC")
	public ResponseEntity<List<Product>> sortProductsById_ASC() {
		List<Product> products = service.sortProductById_ASC();
		if (products != null) {

			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		} else {

			return new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/get-product-by-id_DESC")
	public ResponseEntity<List<Product>> sortProductsByName_DESC() {
		List<Product> products = service.sortProductById_DESC();
		if (products != null) {

			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		} else {

			return new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/get-max-price-products")
	public ResponseEntity<List<Product>> getMaxPriceProducts() {

		List<Product> list = service.getMaxPriceProducts();
		if (list != null) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} else {

			return new ResponseEntity<List<Product>>(list, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/sum-of-product-price")
	public ResponseEntity<Double> countSumOfProductPrice() {
		double sum = service.countSumOfProductPrice();

		return new ResponseEntity<Double>(sum, HttpStatus.OK);

	}

	@GetMapping(value = "/get-total-count-of-products")
	public ResponseEntity<Integer> getTotalCountOfProducts() {
		int count = service.getTotalCountOfProducts();

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}

	@GetMapping(value = "/get-finalproduct-by-id/{id}")

	public ResponseEntity<FinalProduct> getFinalProductById(@PathVariable long id) {
		FinalProduct finalProduct = service.getfinalProductById(id);
		if (finalProduct != null) {
			return new ResponseEntity<FinalProduct>(finalProduct, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@PostMapping("/import-sheet") 
	public ResponseEntity<String> getExcelSheet(@RequestParam MultipartFile file) {
		service.fileWriting(file);
		return null;

	}

}
