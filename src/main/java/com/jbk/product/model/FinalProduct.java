package com.jbk.product.model;

import com.jbk.product.entity.Category;
import com.jbk.product.entity.Supplier;

public class FinalProduct {

	private long productId;
	private String productName;
	private Supplier supplier;
	private Category category;
	private int productQTY;
	private double productPrice;
	private Charges charges;
	private double discountAmount;
	private double gstAmount;
	private double finalProductPrice;

	public FinalProduct() {
		super();
		// TODO Auto-generated constructor stub
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

	public Charges getCharges() {
		return charges;
	}

	public void setCharges(Charges charges) {
		this.charges = charges;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(double gstAmount) {
		this.gstAmount = gstAmount;
	}

	public double getFinalProductPrice() {
		return finalProductPrice;
	}

	public void setFinalProductPrice(double finalProductPrice) {
		this.finalProductPrice = finalProductPrice;
	}

	@Override
	public String toString() {
		return "FinalProduct [productId=" + productId + ", productName=" + productName + ", supplier=" + supplier
				+ ", category=" + category + ", productQTY=" + productQTY + ", productPrice=" + productPrice
				+ ", charges=" + charges + ", discountAmount=" + discountAmount + ", gstAmount=" + gstAmount
				+ ", finalProductPrice=" + finalProductPrice + "]";
	}

}
