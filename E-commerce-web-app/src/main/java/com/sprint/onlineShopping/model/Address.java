package com.sprint.onlineShopping.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "address")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "address", schema = "project")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	private String street;
	private String addressLine1;
	private String city;
	private String state;
	private Integer pincode;
	private Integer buyerId;
	private Long primaryPhone;
	private Long secondaryPhone;

	public Address() {
	}

	public Address(Integer addressId, String street, String addressLine1, String city, String state, Integer pincode,
			Integer buyerId, Long primaryPhone, Long secondaryPhone) {
		super();
		this.addressId = addressId;
		this.street = street;
		this.addressLine1 = addressLine1;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.buyerId = buyerId;
		this.primaryPhone = primaryPhone;
		this.secondaryPhone = secondaryPhone;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public Long getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(Long primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public Long getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setSecondaryPhone(Long secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", street=" + street + ", addressLine1=" + addressLine1 + ", city="
				+ city + ", state=" + state + ", pincode=" + pincode + ", buyerId=" + buyerId + ", primaryPhone="
				+ primaryPhone + ", secondaryPhone=" + secondaryPhone + "]";
	}

}
