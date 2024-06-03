package com.sprint.onlineShopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.onlineShopping.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
