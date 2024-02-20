package com.jbk.product.service;

import com.jbk.product.entity.Supplier;

public interface SupplierService {

	public boolean saveSupplier(Supplier supplier);

	public Supplier getSupplierById(long supplierId);

	public Supplier getSupplierByName(String supplierName);

}
