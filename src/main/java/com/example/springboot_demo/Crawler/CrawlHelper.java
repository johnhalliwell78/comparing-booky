package com.example.springboot_demo.Crawler;

import com.example.springboot_demo.repository.ProductRepository;

public class CrawlHelper {
    private static ProductRepository productRepository;

    public static String fixContent(String content) {
        return "<root>" + content.replaceAll("\\s{3,}", "").replaceAll("\t", "").replaceAll("  ", "").replace("&","và") + "</root>";
    }

    public static String fixRayBanStoreContent(String content) {
        return content.replaceAll("<ul.*?</ul>", "");
    }
}
