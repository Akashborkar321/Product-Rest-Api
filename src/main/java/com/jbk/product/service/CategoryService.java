package com.jbk.product.service;

import com.jbk.product.entity.Category;

public interface CategoryService {

	public boolean saveCategory(Category category);

	public Category getCategoryById(long categoryId);

	public Category getCategoryByName(String categoryName);

}
