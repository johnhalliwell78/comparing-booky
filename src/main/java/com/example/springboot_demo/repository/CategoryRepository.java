package com.example.springboot_demo.repository;

import com.example.springboot_demo.jaxb.product.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAll();

}
