package com.sprint.onlineShopping.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.onlineShopping.model.CartDetail;
import com.sprint.onlineShopping.repository.CartDetailRepository;

@RestController
@RequestMapping("/cartdetails")
public class CartDetailController {
	@Autowired
	private CartDetailRepository cartdetailRepository;

	@GetMapping("/getall")
	public List<CartDetail> list() {
		return cartdetailRepository.findAll();
	}

	@GetMapping("/get/{id}")
	public CartDetail get(@PathVariable Integer id) {
		return cartdetailRepository.getOne(id);
	}

	@PostMapping("/add")
	public String create(@RequestBody final CartDetail cartdetail) {
		cartdetailRepository.saveAndFlush(cartdetail);
		return "Added successfully";
	}

	@DeleteMapping("/remove/{id}")
	public String delete(@PathVariable Integer id) {
		cartdetailRepository.deleteById(id);
		return "CartDetail Deleted successfully";
	}

	@PutMapping("/update/{id}")
	public CartDetail update(@PathVariable Integer id, @RequestBody CartDetail cartdetail) {
		CartDetail existingCartDetail = cartdetailRepository.getOne(id);
		BeanUtils.copyProperties(cartdetail, existingCartDetail, "cartdetailId");
		return cartdetailRepository.saveAndFlush(existingCartDetail);
	}
}
