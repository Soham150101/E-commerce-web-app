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

import com.sprint.onlineShopping.model.Address;
import com.sprint.onlineShopping.model.Category;
import com.sprint.onlineShopping.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
    @Autowired
    CategoryRepository categoryRepository;
    
    @GetMapping("/getall")
	public List<Category> list() {
		return categoryRepository.findAll();
	}

	@GetMapping("/get/{id}")
	public Category get(@PathVariable Integer id) {
		return categoryRepository.getOne(id);
	}

	@PostMapping("/add")
	public Category create(@RequestBody Category category) {
		return categoryRepository.saveAndFlush(category);
	}

	@DeleteMapping("/remove/{id}")
	public String delete(@PathVariable Integer id) {
		categoryRepository.deleteById(id);
		return "Category Deleted successfully";
	}

	@PutMapping("/update/{id}")
	public Category update(@PathVariable Integer id, @RequestBody Category category) {
		Category existingCategory = categoryRepository.getOne(id);
		BeanUtils.copyProperties(category, existingCategory, "categoryId");
		return categoryRepository.saveAndFlush(existingCategory);
	}
}

