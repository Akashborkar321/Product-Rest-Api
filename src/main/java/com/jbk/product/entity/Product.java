package com.jbk.product.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Product {

	@Id
	@Min(1)
	private long productId;
	@NotNull(message = "product name is required")
	private String productName;
	@OneToOne
	private Supplier supplier;
	@OneToOne
	private Category category;
	private int productQTY;
	private double productPrice;

	public Product() {
		super();
	}

	public Product(long productId, String productName, Supplier supplier, Category category, int productQTY,
			double productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.supplier = supplier;
		this.category = category;
		this.productQTY = productQTY;
		this.productPrice = productPrice;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getProductQTY() {
		return productQTY;
	}

	public void setProductQTY(int productQTY) {
		this.productQTY = productQTY;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", supplier=" + supplier
				+ ", category=" + category + ", productQTY=" + productQTY + ", productPrice=" + productPrice + "]";
	}

}
