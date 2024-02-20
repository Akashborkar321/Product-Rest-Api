package com.jbk.product.dao;

import com.jbk.product.entity.Category;

public interface CategoryDao {
	
	public boolean saveCategory(Category category);

	public Category getCategoryById(long categoryId);

	public Category getCategoryByName(String categoryName);


}
