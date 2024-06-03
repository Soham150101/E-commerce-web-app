package com.sprint.onlineShopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.onlineShopping.model.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer>  {

	List<OrderDetails> findByProductId(Integer id);

}
