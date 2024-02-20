package com.jbk.product.daoIMPL;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.product.dao.SupplierDao;
import com.jbk.product.entity.Supplier;

@Repository
public class SupplierDaoIMPL implements SupplierDao {

	@Autowired
	SessionFactory factory;

	@Override
	public boolean saveSupplier(Supplier supplier) {

		Session session = factory.openSession();
		boolean isAdded = false;
		Transaction transaction = session.beginTransaction();
		try {
			Supplier dbSupplier = session.get(Supplier.class, supplier.getSupplierId());
			if (dbSupplier == null) {
				session.save(supplier);
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
	public Supplier getSupplierById(long supplierId) {
		Session session = factory.openSession();
		Supplier supplier = null;
		try {
			supplier = session.get(Supplier.class, supplierId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return supplier;
	}

	@Override
	public Supplier getSupplierByName(String supplierName) {
		Session session = factory.openSession();
		Supplier supplier = null;
		try {
			Criteria criteria = session.createCriteria(Supplier.class);
			criteria.add(Restrictions.eq("supplierName", supplierName));
			supplier = (Supplier) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return supplier;
	
	}

}
