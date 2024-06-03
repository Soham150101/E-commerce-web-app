package com.sprint.onlineShopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.onlineShopping.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	List<Address> findByBuyerId(Integer id);
}
