package com.sprint.onlineShopping.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.sprint.onlineShopping.model.Product;


public interface ProductRepository extends JpaRepository<Product,Integer>  {
	List<Product> findAllByProductCategory(Integer id);
}
