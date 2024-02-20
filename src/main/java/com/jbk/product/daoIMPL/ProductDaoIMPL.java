package com.jbk.product.daoIMPL;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.product.dao.ProductDao;
import com.jbk.product.entity.Product;

@Repository

public class ProductDaoIMPL implements ProductDao {

	@Autowired
	SessionFactory factory;

	@Override
	public boolean saveProduct(Product product) {
		Session session = factory.openSession();
		boolean isAdded = false;
		Transaction transaction = session.beginTransaction();
		try {
			Product dbProduct = session.get(Product.class, product.getProductId());
			if (dbProduct == null) {
				session.save(product);
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
	public Product getProductById(long productId) {
		Session session = factory.openSession();
		Product product = null;
		try {
			product = session.get(Product.class, productId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		Session session = factory.openSession();
		List<Product> list = null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

		}
		return list;
	}

	@Override
	public boolean deleteProductById(long productId) {
		Session session = factory.openSession();
		boolean isDeleted = false;
		try {
			Transaction transaction = session.beginTransaction();
			Product dbProduct = session.get(Product.class, productId);
			if (dbProduct != null) {
				session.delete(dbProduct);
				transaction.commit();
				isDeleted = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return isDeleted;
	}

	@Override
	public boolean updateProduct(Product product) {
		Session session = factory.openSession();
		boolean isUpdated = false;
		try {
			Transaction transaction = session.beginTransaction();
			Product dbProduct = session.get(Product.class, product.getProductId());
			if (dbProduct != null) {
				session.evict(dbProduct);
				session.update(product);
				transaction.commit();
				isUpdated = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return isUpdated;
	}

	@Override
	public List<Product> sortProductById_ASC() {
		Session session = factory.openSession();
		List<Product> list = null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			list = criteria.addOrder(Order.asc("productId")).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public List<Product> sortProductById_DESC() {
		Session session = factory.openSession();
		List<Product> list = null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			list = criteria.addOrder(Order.desc("productId")).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public List<Product> getMaxPriceProducts() {
		Session session = factory.openSession();
		List<Product> list = null;

		try {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.max("productPrice"));
			double maxPrice = (double) criteria.uniqueResult();
			list = session.createCriteria(Product.class).add(Restrictions.eq("productPrice", maxPrice)).list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public double countSumOfProductPrice() {
		Session session = factory.openSession();
		double sum = 0.0;

		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.sum("productPrice"));
			@SuppressWarnings("unchecked")
			List<Double> list = criteria.list();
			sum = list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sum;
	}

	@Override
	public int getTotalCountOfProducts() {
		Session session = factory.openSession();
		long count = 0;

		try {

			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.rowCount());
			List<Long> list = criteria.list();
			if (!list.isEmpty()) {
				count = list.get(0);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return (int) count;
	}

}
