package com.example.springboot_demo.service;

import com.example.springboot_demo.jaxb.product.category.Category;
import com.example.springboot_demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
    public Optional<Category> getByKey(String key) {
        return null;
    }


}
