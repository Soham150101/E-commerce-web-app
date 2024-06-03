package com.sprint.onlineShopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.sprint.onlineShopping.model.Category;


public interface CategoryRepository extends JpaRepository<Category,Integer>  {

}
