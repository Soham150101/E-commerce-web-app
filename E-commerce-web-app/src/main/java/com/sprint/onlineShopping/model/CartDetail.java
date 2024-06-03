package com.sprint.onlineShopping.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "CartDetails")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "CartDetails", schema = "project")

public class CartDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartDetailId;
	private Integer cartId;
	private Integer productId;

	public CartDetail() {
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	

	public CartDetail(Integer cartDetailId, Integer cartId, Integer productId) {
		super();
		this.cartDetailId = cartDetailId;
		this.cartId = cartId;
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "CartDetail [cartDetailId=" + cartDetailId + ", cartId=" + cartId + ", productId=" + productId + "]";
	}

	

}
