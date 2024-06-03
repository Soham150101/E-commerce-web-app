package com.sprint.onlineShopping.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.sprint.onlineShopping.model.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>  {
	
	List<Order> findByBuyerId(Integer id);
	
	
	@Query(value = "select o.* from project.order o inner join project.address a on o.address_id =a.address_id where a.city =:c ", nativeQuery=true )
	List<Order> getOrderByCity(@Param("c") String city);
	
	
	@Modifying
	@Query(value = "SELECT * FROM project.order WHERE order_date =:d ", nativeQuery=true )
	List<Order> getOrderByDate(@Param("d") LocalDate date);


	List<Order> findAllByBuyerId(Integer intValue);

	
}
