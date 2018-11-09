package com.example.springboot_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

@Controller
public class PageController {
    private static final int NEWS_PER_PAGE = 4;

    //    private final NewsService newsService;
//    private final CategoryService categoryService;
    @GetMapping(value = "/home")
    public ModelAndView home(HttpServletRequest request) throws JAXBException {
//        List<Category> categories = categoryService.getAllSortedByCategoryKey();

        HttpSession session = request.getSession();
//        session.setAttribute("CATEGORIES", categories);
        return new ModelAndView("index");
    }


    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new ModelAndView("login");
    }


}
