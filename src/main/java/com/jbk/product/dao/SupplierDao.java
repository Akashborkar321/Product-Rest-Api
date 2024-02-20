package com.jbk.product.dao;

import com.jbk.product.entity.Supplier;

public interface SupplierDao {

	public boolean saveSupplier(Supplier supplier);

	public Supplier getSupplierById(long supplierId);

	public Supplier getSupplierByName(String supplierName);

}
