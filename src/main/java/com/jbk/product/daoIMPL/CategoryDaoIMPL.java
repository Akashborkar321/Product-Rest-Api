package com.jbk.product.daoIMPL;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.product.dao.CategoryDao;
import com.jbk.product.entity.Category;

@Repository
public class CategoryDaoIMPL implements CategoryDao {
	@Autowired
	private SessionFactory factory;

	@Override
	public boolean saveCategory(Category category) {
		Session session = factory.openSession();
		boolean isAdded = false;
		Transaction transaction = session.beginTransaction();
		try {
			Category dbCategory = session.get(Category.class, category.getCategoryId());
			if (dbCategory == null) {
				session.save(category);
				transaction.commit();
				isAdded = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

		}
		return isAdded;
	}

	@Override
	public Category getCategoryById(long categoryId) {
		Session session = factory.openSession();
		Category category = null;
		try {
			category = session.get(Category.class, categoryId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return category;
	}

	@Override
	public Category getCategoryByName(String categoryName) {
		Session session = factory.openSession();
		Category category = null;
		try {
			Criteria criteria = session.createCriteria(Category.class);
			List<Category> list = criteria.list();
			for (Category dbCategory : list) {
				if (dbCategory.getCategoryName().equalsIgnoreCase(categoryName)) {
					category = dbCategory;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return category;
	}

}
