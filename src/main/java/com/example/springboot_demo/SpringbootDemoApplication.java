package com.example.springboot_demo;

import com.example.springboot_demo.jaxb.product.Product;
import com.example.springboot_demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class SpringbootDemoApplication {

    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }

//    @RequestMapping("/hello")
//    public String sayHello() {
//        return "Hello";
//    }
//
//    @GetMapping(path = "/add") // Map ONLY GET Requests
//    public @ResponseBody
//    String addNewUser(@RequestParam String name
//            , @RequestParam String email) {
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//
//        Product product = new Product();
//        product.setCategory("dsa");
//        product.setId(3);
//        product.setName("dsads");
//        product.setProductLink("dsads");
//        product.setAuthor("dsads");
//        product.setCategory("dsads");
//        product.setCategoryKey("dsads");
//        product.setImageLinks("dsadsa");
//        product.setAvatarLink("dsads");
//        product.setSiteKey("dsad");
//        product.setCode("dsads");
//        product.setPrice(43243);
//        product.setModel("dsads");
//
//
//        productRepository.save(product);
//
//        return "Saved";
//    }
//
//    @GetMapping(path = "/all", produces = {"application/xml", "text/xml"}, consumes = MediaType.ALL_VALUE)
//    public @ResponseBody
//    Iterable<Product> getAllUsers() {
//        // This returns a JSON or XML with the users
//        return productRepository.findAll();
//    }

}
