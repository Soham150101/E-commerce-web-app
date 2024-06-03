package com.sprint.onlineShopping.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.onlineShopping.model.Buyer;
import com.sprint.onlineShopping.model.Order;
import com.sprint.onlineShopping.repository.AddressRepository;
import com.sprint.onlineShopping.repository.OrderRepository;

@RestController
@RequestMapping("/orders")

public class OrderController {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private AddressRepository addressRepository;

	@GetMapping("/getall")
	public List<Order> list() {
		return orderRepository.findAll();
	}

	@GetMapping("/get/{id}")
	public Order get(@PathVariable Integer id) {
		return orderRepository.getOne(id);
	}

	@PostMapping("/add")
	public Order create(@RequestBody final Order order) {
		return orderRepository.saveAndFlush(order);
	}

	@DeleteMapping("/remove/{id}")
	public String delete(@PathVariable Integer id) {
		orderRepository.deleteById(id);
		return "Order Removed successfuly";
	}

	@PutMapping("/update/{id}")
	public Order update(@PathVariable Integer id, @RequestBody Order order) {
		Order existingOrder = orderRepository.getOne(id);
		BeanUtils.copyProperties(order, existingOrder, "orderId");
		return orderRepository.saveAndFlush(existingOrder);
	}

	@GetMapping("/getOrder/{id}")
	public List<Order> buyerOrder(@PathVariable Integer id) {
		return orderRepository.findByBuyerId(id);
	}

	@GetMapping("/getOrder")
	public List<Order> orderbyAddress(@RequestParam("city") String city) {
		return orderRepository.getOrderByCity(city);
	}
	

	@GetMapping("/getOrderByDate")
	public List<Order> orderbyDate(
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

		return orderRepository.getOrderByDate(date);
	}
}
