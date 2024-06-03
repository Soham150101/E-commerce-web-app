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

import com.sprint.onlineShopping.model.Cart;
import com.sprint.onlineShopping.repository.CartRepository;

@RestController
@RequestMapping("/carts")
public class CartController {
	@Autowired
	private CartRepository  cartRepository;

	@GetMapping("/getall")
	public List<Cart> list() {
		return cartRepository.findAll();
	}

	@GetMapping("/get/{id}")
	public Cart get(@PathVariable Integer id) {
		return cartRepository.getOne(id);
	}

	@PostMapping("/add")
	public Cart create(@RequestBody final Cart cart) {
		return cartRepository.saveAndFlush(cart);
	}

	
	@DeleteMapping("/remove/{id}")
	public String delete(@PathVariable Integer id) {
		cartRepository.deleteById(id);
		return "cart Deleted successfully";
	}

	
	@PutMapping("/update/{id}")
	public Cart update(@PathVariable Integer id, @RequestBody Cart cart) {
		Cart existingCart = cartRepository.getOne(id);
		BeanUtils.copyProperties(cart, existingCart, "cartId");
		return cartRepository.saveAndFlush(existingCart);
	}

}
