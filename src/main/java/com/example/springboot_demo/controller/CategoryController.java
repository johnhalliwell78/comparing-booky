package com.example.springboot_demo.controller;

import com.example.springboot_demo.jaxb.product.category.Categories;
import com.example.springboot_demo.jaxb.product.category.Category;
import com.example.springboot_demo.service.CategoryService;
import com.example.springboot_demo.utils.JaxbUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.util.Optional;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/categories", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity getAll() {
        Categories categories = new Categories();
        categories.setCategories(categoryService.getAll());

        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

//    @GetMapping(value = "/categories/{categoryKey}", produces = MediaType.APPLICATION_XML_VALUE)
//    public ResponseEntity<Category> getByKey(@PathVariable("categoryKey") String categoryKey) {
//        return categoryService.getByKey(categoryKey)
//                .map(category -> ResponseEntity.status(HttpStatus.OK).body(category))
//                .orElseGet(ResponseEntity.status(HttpStatus.NOT_FOUND)::build);
//    }

    @GetMapping(value = "/categories/{categoryKey}")
    public ModelAndView introduce(HttpServletRequest request,
                                  @PathVariable("categoryKey") String categoryKey) throws JAXBException {
        Optional<Category> optional = categoryService.getByKey(categoryKey);

        if (optional.isPresent()) {
            Category category = optional.get();

            request.setAttribute("CATEGORY", JaxbUtils.marshall(category));

            return new ModelAndView("introduce");
        }

        return new ModelAndView("not-found");
    }
}
