package com.jbk.product.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.product.dao.SupplierDao;
import com.jbk.product.entity.Supplier;
import com.jbk.product.service.SupplierService;

@Service
public class SupplierServiceIMPL implements SupplierService {

	@Autowired
	SupplierDao supplierDao;

	@Override
	public boolean saveSupplier(Supplier supplier) {
		boolean isAdded = supplierDao.saveSupplier(supplier);
		return isAdded;
	}

	@Override
	public Supplier getSupplierById(long supplierId) {
		Supplier supplier = supplierDao.getSupplierById(supplierId);
		return supplier;
	}

	@Override
	public Supplier getSupplierByName(String supplierName) {
		Supplier supplier = supplierDao.getSupplierByName(supplierName);
		return supplier;
	}

}
