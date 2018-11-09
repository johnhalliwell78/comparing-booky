package com.example.springboot_demo.repository;

import com.example.springboot_demo.jaxb.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
//    Page<Product> findAll(Pageable pageable);


    @Query("SELECT DISTINCT p.model FROM Product p WHERE p.siteKey = :siteKey AND p.categoryKey = :categoryKey")
    Page<String> findModels(@Param("siteKey") String siteKey,
                            @Param("categoryKey") String categoryKey,
                            Pageable pageable);

    Integer countByProductLink(String productLink);

    Integer countByCode(String code);

    Page<Product> findByNameContainingIgnoreCaseAndCodeContains(String name, String code, Pageable pageable);


//    @Query("SELECT p FROM Product p WHERE (p.name LIKE p)")
//    Page<Product> findByNameAndCode(@Param("name") String name,
//                                    @Param("code") String code,
//                                    Pageable pageable);

    Optional<Product> findById(String id);

//    Page<Product> findByCode(@Param("code") String code);

    @Query("SELECT p FROM Product p WHERE (p.code = :code OR :codeLike LIKE p.code OR p.model = :model) AND p.siteKey = :siteKey AND p.categoryKey = :categoryKey " +
            "ORDER BY CASE WHEN p.code = :code THEN 1 ELSE 2 END")
    Page<Product> findByCodeOrModelAndSiteKeyAndCategoryKey(@Param("code") String code,
                                                            @Param("codeLike") String codeLike,
                                                            @Param("model") String model,
                                                            @Param("siteKey") String siteKey,
                                                            @Param("categoryKey") String categoryKey,
                                                            Pageable pageable);

    Page<Product> findBySiteKey(String siteKey, Pageable pageable);

    Page<Product> findBySiteKeyAndCategoryKey(String siteKey, String categoryKey, Pageable pageable);


    @Query("SELECT p FROM Product p WHERE (p.code LIKE :searchValue OR p.name LIKE :searchValue OR p.category LIKE :searchValue OR p.author LIKE :searchValue OR p.category LIKE :searchValue) AND p.siteKey = :siteKey")
    Page<Product> findBySearchValue(@Param("searchValue") String searchValue, @Param("siteKey") String siteKey, Pageable pageable);


    @Query("SELECT p FROM Product p WHERE p.code LIKE :searchValue OR p.name LIKE :searchValue OR p.category LIKE :searchValue OR p.author LIKE :searchValue OR p.category LIKE :searchValue")
    Page<Product> findBySearchValue(@Param("searchValue") String searchValue, Pageable pageable);


    @Query("SELECT DISTINCT p.categoryKey FROM Product p WHERE p.siteKey = :siteKey")
    List<String> getCategoryKeyBySiteKey(@Param("siteKey") String siteKey);
}
