package com.sprint.onlineShopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.onlineShopping.model.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
	Buyer findByEmailEqualsAndPasswordEquals(String email, String password);

	Buyer findByEmail(String email);
}
