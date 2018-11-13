package com.example.springboot_demo.service;

import com.example.springboot_demo.jaxb.product.Product;
import com.example.springboot_demo.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getById(int ID) {
        return productRepository.findById(ID);
    }

    public Page<Product> findByNameAndCodeContains(String name, String code) {
        Pageable pageable = PageRequest.of(1 - 1, 3);
        return productRepository.findByNameContainingIgnoreCaseAndCodeContains(name, code, pageable);
    }

    public Page<Product> search(String searchValue, String siteKey, int pageNumber, int itemsPerPage) {
        Pageable pageable = PageRequest.of(pageNumber - 1, itemsPerPage);
        String search = "%" + searchValue + "%";
        return productRepository.findBySearchValue(search, siteKey, pageable);
    }

    public Page<Product> search(String searchValue, int pageNumber, int itemsPerPage) {
        Pageable pageable = PageRequest.of(pageNumber - 1, itemsPerPage);
        String search = "%" + searchValue + "%";
        return productRepository.findBySearchValue(search, pageable);
    }

    public Page<Product> getBySiteKey(String siteKey, int pageNumber, int itemsPerPage) {
        Pageable pageable = PageRequest.of(pageNumber - 1, itemsPerPage);
        return productRepository.findBySiteKey(siteKey, pageable);
    }

    public Page<Product> getBySiteKeyAndCategoryKey(String siteKey, String categoryKey, int pageNumber, int itemsPerPage) {
        Pageable pageable = PageRequest.of(pageNumber - 1, itemsPerPage);
        return productRepository.findBySiteKeyAndCategoryKey(siteKey, categoryKey, pageable);
    }



}
