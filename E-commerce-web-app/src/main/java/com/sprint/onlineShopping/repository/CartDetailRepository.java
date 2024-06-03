package com.sprint.onlineShopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.onlineShopping.model.CartDetail;

public interface CartDetailRepository extends JpaRepository<CartDetail, Integer>{

	List<CartDetail> findAllByCartId(Integer id);
}
