package com.sprint.onlineShopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.onlineShopping.model.Address;
import com.sprint.onlineShopping.model.Buyer;
import com.sprint.onlineShopping.model.Order;
import com.sprint.onlineShopping.model.OrderDetails;
import com.sprint.onlineShopping.repository.OrderDetailsRepository;

@RestController
@RequestMapping("/orderDetails")
public class OrderDetailsController {
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	@GetMapping("/getall")
	public List<OrderDetails> list() {
		return orderDetailsRepository.findAll();
	}
	
	@GetMapping("/getOrderDetailsByProduct/{id}")
	public List<OrderDetails> productOrderDetails(@PathVariable Integer id) {
		return orderDetailsRepository.findByProductId(id);
	}
	
	@PostMapping("/add")
	public OrderDetails create(@RequestBody OrderDetails orderDetails) {
		return orderDetailsRepository.saveAndFlush(orderDetails);
	}
	
}
