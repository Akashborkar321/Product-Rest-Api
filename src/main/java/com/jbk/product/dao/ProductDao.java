package com.jbk.product.dao;

import java.util.List;

import com.jbk.product.entity.Product;

public interface ProductDao {
	public boolean saveProduct(Product product);

	public Product getProductById(long productId);

	public List<Product> getAllProducts();

	public boolean deleteProductById(long productId);

	public boolean updateProduct(Product product);

	public List<Product> sortProductById_ASC();

	public List<Product> sortProductById_DESC();

	public List<Product> getMaxPriceProducts();

	public double countSumOfProductPrice();

	public int getTotalCountOfProducts();

}
