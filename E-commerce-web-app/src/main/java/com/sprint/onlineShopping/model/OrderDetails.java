package com.sprint.onlineShopping.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "OrderDetails")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "OrderDetails", schema = "project")
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderDetailId;
	private Integer orderId;
	private Integer productId;
	public OrderDetails() {
	}
	
	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public OrderDetails(Integer orderDetailId,Integer  orderId, Integer productId) {
		super();
		this.orderDetailId=orderDetailId;
		this.orderId = orderId;
		this.productId = productId;
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", productId=" + productId + "]";
	}
	
}
