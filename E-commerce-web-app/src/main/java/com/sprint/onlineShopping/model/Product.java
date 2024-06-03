package com.sprint.onlineShopping.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "product")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "product", schema = "project")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	private String productName;
	private Integer price;
	private String productDescription;
	private Integer quantity;
	private Integer productCategory;

	public Product() {
	}

	public Product(Integer productId, String productname, Integer price, String productDescriptior, Integer quantity,
			Integer productCategory) {
		this.productId = productId;
		this.productName = productname;
		this.price = price;
		this.productDescription = productDescriptior;
		this.quantity = quantity;
		this.productCategory = productCategory;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductname() {
		return productName;
	}

	public void setProductname(String productname) {
		this.productName = productname;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getProductDescriptior() {
		return productDescription;
	}

	public void setProductDescriptior(String productDescriptior) {
		this.productDescription = productDescriptior;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(Integer productCategory) {
		this.productCategory = productCategory;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productname=" + productName + ", price=" + price
				+ ", productDescriptior=" + productDescription + ", quantity=" + quantity + ", productCategory="
				+ productCategory + "]";
	}

}
