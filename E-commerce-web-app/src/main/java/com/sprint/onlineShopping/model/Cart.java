package com.sprint.onlineShopping.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "Cart")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "Cart", schema = "project")

public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartId;
	private Integer quantity;
	private Integer totalValue;

	public Cart() {
	}

	public Cart(Integer cartId, Integer quantity, Integer totalValue) {
		super();
		this.cartId = cartId;
		this.quantity = quantity;
		this.totalValue = totalValue;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Integer totalValue) {
		this.totalValue = totalValue;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", quantity=" + quantity + ", totalValue=" + totalValue + "]";
	}
	
	
}
