package com.jbk.product.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.product.dao.CategoryDao;
import com.jbk.product.entity.Category;
import com.jbk.product.service.CategoryService;

@Service
public class CategoryServiceIMPL implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

	@Override
	public boolean saveCategory(Category category) {
		boolean isAdded = categoryDao.saveCategory(category);
		return isAdded;
	}

	@Override
	public Category getCategoryById(long categoryId) {
		Category category = categoryDao.getCategoryById(categoryId);
		return category;
	}

	@Override
	public Category getCategoryByName(String categoryName) {
		Category category = categoryDao.getCategoryByName(categoryName);
		return category;		
	}

}
