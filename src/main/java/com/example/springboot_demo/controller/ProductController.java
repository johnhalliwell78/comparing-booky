package com.example.springboot_demo.controller;

import com.example.springboot_demo.constant.KhaiTamConstants;
import com.example.springboot_demo.constant.VanLangConstants;
import com.example.springboot_demo.jaxb.product.Product;
import com.example.springboot_demo.jaxb.product.Products;
import com.example.springboot_demo.service.ProductService;
import com.example.springboot_demo.utils.JaxbUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.util.Optional;

@Controller
public class ProductController {
    private static final int PRODUCTS_PER_PAGE_VIEW_ALL = 12;
    private static final int PRODUCTS_PER_PAGE_SEARCH = 12;

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product-detail/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Product> getById(@PathVariable("id") int id) {
        Optional<Product> optional = productService.getById(id);

        System.out.println("có hay không");
        if (optional.isPresent()) {
            Product product = optional.get();


            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @GetMapping(value = "/products/id={id}")
    public ModelAndView getProduct(HttpServletRequest request,
                                   @PathVariable("id") int id) throws JAXBException {
        Optional<Product> optional = productService.getById(id);

        if (optional.isPresent()) {
            Product product = optional.get();

            request.setAttribute("PRODUCT_ID", id);
            request.setAttribute("NAME", product.getName());
            request.setAttribute("AUTHOR", product.getAuthor());
            request.setAttribute("PRICE", product.getPrice());
            request.setAttribute("PRODUCT_LINK", product.getProductLink());
            request.setAttribute("PRODUCT_CODE", product.getCode());
            request.setAttribute("SITE", product.getSiteKey());


            if (!product.getCode().contains("KT")) {
                Page<Product> productPage = productService.findByNameAndCodeContains(product.getName(), "KT");

                Products products = new Products();
                products.setProducts(productPage.getContent());

                request.setAttribute("KHAITAM_SUGGESTS", JaxbUtils.marshall(products));
                System.out.println(JaxbUtils.marshall(products));
            }
            if (!product.getCode().contains("VL")) {
                Page<Product> productPage = productService.findByNameAndCodeContains(product.getName(), "VL");

                Products products = new Products();
                products.setProducts(productPage.getContent());

                request.setAttribute("VANLANG_SUGGESTS", JaxbUtils.marshall(products));
                System.out.println(JaxbUtils.marshall(products));
            }
            return new ModelAndView("detail");
        }
        return new ModelAndView("not-found");
    }

    @GetMapping(value = "/search")
    public ModelAndView searchPreview(HttpServletRequest request,
                                      @RequestParam("searchValue") String searchValue) throws JAXBException {

        request.setAttribute("SEARCH_VALUE", searchValue);

        request.setAttribute("KHAITAM_SITEKEY", KhaiTamConstants.KHAITAM_SITEKEY);
        request.setAttribute("VANLANG_SITEKEY", VanLangConstants.VANLANG_SITEKEY);

        Page<Product> khaiTambooks = productService.search(searchValue, KhaiTamConstants.KHAITAM_SITEKEY, 1, PRODUCTS_PER_PAGE_SEARCH);
        Page<Product> vanLangbooks = productService.search(searchValue, VanLangConstants.VANLANG_SITEKEY, 1, PRODUCTS_PER_PAGE_SEARCH);
        Page<Product> allBooks = productService.search(searchValue, 1, PRODUCTS_PER_PAGE_SEARCH);

        Products products = new Products();


//        if (!khaiTambooks.getContent().isEmpty()) {
//            products.setProducts(khaiTambooks.getContent());
//
//            request.setAttribute("SEARCH_KHAITAM", JaxbUtils.marshall(products));
//            request.setAttribute("MORESEARCH_KHAITAM", khaiTambooks.getTotalPages());
//        }
//
//        if (!vanLangbooks.getContent().isEmpty()) {
//            products.setProducts(vanLangbooks.getContent());
//
//            request.setAttribute("SEARCH_VANLANG", JaxbUtils.marshall(products));
//            request.setAttribute("MORESEARCH_VANLANG", vanLangbooks.getTotalPages());
//        }
//
//        if (khaiTambooks.getContent().isEmpty() && vanLangbooks.getContent().isEmpty()) {
//            request.setAttribute("NO_RESULT", "Không tìm thấy sách theo từ khóa");
//        }

        if (!allBooks.getContent().isEmpty()) {
            products.setProducts(allBooks.getContent());

            request.setAttribute("SEARCH_ALL", JaxbUtils.marshall(products));
            request.setAttribute("MORESEARCH_ALL", allBooks.getTotalPages());
            request.setAttribute("CURRENT_PAGE", 1);
            request.setAttribute("NUMBER_OF_PAGES", allBooks.getTotalPages());
        }

        if (allBooks.getContent().isEmpty() && allBooks.getContent().isEmpty()) {
            request.setAttribute("NO_RESULT", "Không tìm thấy sách theo từ khóa");
        }
        return new ModelAndView("search-preview");

    }


    @GetMapping(value = "/search/searchValue={searchValue}/page={pageNumber}")
    public ModelAndView searchAll(HttpServletRequest request,
                                  @PathVariable("searchValue") String searchValue,
                                  @PathVariable("pageNumber") int pageNumber) throws JAXBException {

        request.setAttribute("SEARCH_VALUE", searchValue);

        Page<Product> allBooks = productService.search(searchValue, pageNumber, PRODUCTS_PER_PAGE_VIEW_ALL);

        Products products = new Products();

        request.setAttribute("CURRENT_PAGE", pageNumber);
        request.setAttribute("NUMBER_OF_PAGES", allBooks.getTotalPages());

        products.setProducts(allBooks.getContent());


        if (!allBooks.getContent().isEmpty()) {
            products.setProducts(allBooks.getContent());

            request.setAttribute("SEARCH_ALL", JaxbUtils.marshall(products));
            request.setAttribute("MORESEARCH_ALL", allBooks.getTotalPages());
            request.setAttribute("NUMBER_OF_PAGES", allBooks.getTotalPages());
        }

        if (allBooks.getContent().isEmpty() && allBooks.getContent().isEmpty()) {
            request.setAttribute("NO_RESULT", "Không tìm thấy sách theo từ khóa");
        }
        return new ModelAndView("search-preview");
    }

    @GetMapping(value = "/products/{siteKey}")
    public ModelAndView viewAllBySiteKey(HttpServletRequest request,
                                         @PathVariable("siteKey") String siteKey) throws JAXBException {
        Page<Product> productPage = productService.getBySiteKey(siteKey, 1, PRODUCTS_PER_PAGE_VIEW_ALL);

        if (!productPage.getContent().isEmpty()) {
            request.setAttribute("SITE", siteKey);

            request.setAttribute("CURRENT_PAGE", 1);
            request.setAttribute("NUMBER_OF_PAGES", productPage.getTotalPages());

            Products products = new Products();
            products.setProducts(productPage.getContent());

            request.setAttribute("PRODUCTS", JaxbUtils.marshall(products));

            return new ModelAndView("view-all");
        }
        return new ModelAndView("not-found");
    }

    @GetMapping(value = "/products/{siteKey}/page={pageNumber}")
    public ModelAndView ViewAllBySiteKey(HttpServletRequest request,
                                         @PathVariable("siteKey") String siteKey,
                                         @PathVariable("pageNumber") int pageNumber) throws JAXBException {

        Page<Product> productPage = productService.getBySiteKey(siteKey, pageNumber, PRODUCTS_PER_PAGE_VIEW_ALL);

        if (!productPage.getContent().isEmpty()) {

            request.setAttribute("SITE", siteKey);

            request.setAttribute("CURRENT_PAGE", pageNumber);
            request.setAttribute("NUMBER_OF_PAGES", productPage.getTotalPages());

            Products products = new Products();
            products.setProducts(productPage.getContent());

            request.setAttribute("PRODUCTS", JaxbUtils.marshall(products));

            return new ModelAndView("view-all");
        }


        return new ModelAndView("not-found");
    }

    @GetMapping(value = "/products/{siteKey}/{categoryKey}")
    public ModelAndView viewAllByCategory(HttpServletRequest request,
                                          @PathVariable("siteKey") String siteKey,
                                          @PathVariable("categoryKey") String categoryKey) throws JAXBException {
        Page<Product> productPage = productService.getBySiteKeyAndCategoryKey(siteKey, categoryKey, 1, PRODUCTS_PER_PAGE_VIEW_ALL);

        if (!productPage.getContent().isEmpty()) {
            Products products = new Products();
            products.setProducts(productPage.getContent());

            request.setAttribute("CATEGORY", categoryKey);
            request.setAttribute("SITE", siteKey);

            request.setAttribute("CURRENT_PAGE", 1);
            request.setAttribute("NUMBER_OF_PAGES", productPage.getTotalPages());

            request.setAttribute("PRODUCTS", JaxbUtils.marshall(products));
            return new ModelAndView("view-all");

        }


        return new ModelAndView("not-found");
    }


    @GetMapping(value = "/products/{siteKey}/{categoryKey}/page={pageNumber}")
    public ModelAndView viewAllByCategory(HttpServletRequest request,
                                          @PathVariable("siteKey") String siteKey,
                                          @PathVariable("categoryKey") String categoryKey,
                                          @PathVariable("pageNumber") int pageNumber) throws JAXBException {
        Page<Product> productPage = productService.getBySiteKeyAndCategoryKey(siteKey, categoryKey, pageNumber, PRODUCTS_PER_PAGE_VIEW_ALL);

        if (!productPage.getContent().isEmpty()) {
            Products products = new Products();
            products.setProducts(productPage.getContent());

            request.setAttribute("CATEGORY", categoryKey);
            request.setAttribute("SITE", siteKey);

            request.setAttribute("CURRENT_PAGE", pageNumber);
            request.setAttribute("NUMBER_OF_PAGES", productPage.getTotalPages());

            request.setAttribute("PRODUCTS", JaxbUtils.marshall(products));
            return new ModelAndView("view-category");

        }


        return new ModelAndView("not-found");
    }


}
