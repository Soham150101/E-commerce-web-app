package com.sprint.onlineShopping.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "Buyer")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "Buyer", schema = "project")
public class Buyer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer buyerId;
	private String name;
	private String email;
	private String password;
	private Integer cartId;
	private LocalDate dob;

//	private List<Order> orders;
	
	
//	@OneToMany(
//			
//			)
//	private List<Address> addresses;

	public Buyer() {
	}

	public Buyer(Integer buyerId, String name, String email, String password, Integer cartId, LocalDate dob) {
		super();
		this.buyerId = buyerId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.cartId = cartId;
		this.dob = dob;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Buyer [buyerId=" + buyerId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", cartId=" + cartId + ", dob=" + dob + "]";
	}
	
	

}
