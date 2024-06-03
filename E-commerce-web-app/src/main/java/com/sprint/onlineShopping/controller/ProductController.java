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

import com.sprint.onlineShopping.model.Product;
import com.sprint.onlineShopping.repository.CategoryRepository;
import com.sprint.onlineShopping.repository.ProductRepository;

@RequestMapping("/products")
@RestController
public class ProductController {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	@GetMapping("/getall")
	public List<Product> list() {
		return productRepository.findAll();
	}

	@GetMapping("/get/{productId}")
	public Product showOne(@PathVariable("productId") Integer id) {
		return productRepository.getOne(id);
	}
	
	@GetMapping("/getByCategory/{categoryId}")
	public List<Product> productByCategory(@PathVariable("categoryId") Integer id) {
		return productRepository.findAllByProductCategory(id);
	}	

	@PostMapping("/add")
	public Product create(@RequestBody final Product product) {
		return productRepository.saveAndFlush(product);

	}

	@DeleteMapping("/remove/{id}")
	public String delete(@PathVariable Integer id) {
		productRepository.deleteById(id);
		return "Product Deleted successfuly";
	}

	@PutMapping("/update/{id}")
	public Product update(@PathVariable Integer id, @RequestBody Product product) {
		Product existingProduct = productRepository.getOne(id);
		BeanUtils.copyProperties(product, existingProduct, "productId");
		return productRepository.saveAndFlush(existingProduct);
	}
}
