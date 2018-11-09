package com.example.springboot_demo.controller;


import com.example.springboot_demo.model.User;
import com.example.springboot_demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(HttpServletRequest request,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password) throws JAXBException {
        Optional<User> optional = userService.getUser(username, password);
        System.out.println(username);
        System.out.println(password);
        if (optional.isPresent()) {
            User user = optional.get();


            HttpSession session = request.getSession();

            session.setAttribute("USER_NAME", user.getName());
            session.setAttribute("USER_ROLE", user.getRole());

            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
