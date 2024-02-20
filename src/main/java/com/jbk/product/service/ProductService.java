package com.jbk.product.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jbk.product.entity.Product;
import com.jbk.product.model.FinalProduct;

public interface ProductService {

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

	public FinalProduct getfinalProductById(long id);

	public void fileWriting(MultipartFile file);

	public List<Product> readSheet(String path);

}
