package com.example.springboot_demo.controller;


import com.example.springboot_demo.service.CrawlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CrawlController {
    private final CrawlService crawlService;

    public CrawlController(CrawlService crawlService) {
        this.crawlService = crawlService;
    }

    @RequestMapping("/crawl-data")
    public ResponseEntity crawlData(@RequestParam("status") String status) {
        if (status.equals("run")) {
            crawlService.crawlData(true);
        } else if (status.equals("stop")) {
            crawlService.crawlData(false);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
