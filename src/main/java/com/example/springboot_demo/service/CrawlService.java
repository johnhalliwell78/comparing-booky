package com.example.springboot_demo.service;

import com.example.springboot_demo.Crawler.CrawlHelper;
import com.example.springboot_demo.Crawler.Crawler_Book;
import com.example.springboot_demo.Parser.Parser;
import com.example.springboot_demo.constant.KhaiTamConstants;
import com.example.springboot_demo.constant.VanLangConstants;
import com.example.springboot_demo.jaxb.product.Product;
import com.example.springboot_demo.jaxb.product.category.Category;
import com.example.springboot_demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.springboot_demo.constant.CategoryConstants.*;

@Service
public class CrawlService {
    private static boolean isCrawling = false;
    private final ProductRepository productRepository;


    public CrawlService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    private void CrawlProductFromKhaiTam() {
//        Crawler_Book.parseHTML_getPageCount(BOOK_KHAI_TAM);
//        int pageCount_Khaitam = Crawler_Book.pageCount_KhaiTam;
//
//        for (int i = 1; i <= pageCount_Khaitam && isCrawling; i++) {
//            String url = BOOK_KHAI_TAM + "?pagenumber=" + i;
//
//            Crawler_Book.parseHTML(url,
//                    KhaiTamConstants.KHAITAM_BEGINSIGN,
//                    KhaiTamConstants.KHAITAM_ENDSIGN);
//
//            String content = CrawlHelper.fixContent(Crawler_Book.htmlContent);
//
//            List<Product> products = Parser.getProductListFromKhaiTamBook(content);
//            saveProducts(products);
//            System.out.println("Saved " + products.size() + " products");
//        }
//    }

//    private void CrawlProductFromVanLang() {
//        Crawler_Book.parseHTML_getPageCount(BOOK_VAN_LANG);
//        int pageCount_Vanlang = Crawler_Book.pageCount_VangLang;
//
//        for (int i = 0; i <= pageCount_Vanlang && isCrawling; i++) {
//            String url = BOOK_VAN_LANG +
//                    "?pagesize=" + VanLangConstants.VANLANG_PAGESIZE +
//                    "&pagenumber=" + i;
//
//            Crawler_Book.parseHTML(url,
//                    VanLangConstants.VANLANG_BEGINSIGN,
//                    VanLangConstants.VANLANG_ENDSIGN);
//
//            String content = CrawlHelper.fixContent(Crawler_Book.htmlContent);
//
//            List<Product> products = Parser.getProductListFromVanLangBook(content);
//            saveProducts(products);
//            System.out.println("Saved " + products.size() + " products");
//        }
//    }

    private void crawlProduct() {

        Crawler_Book.parseHTML(CATEGORY_DOMAIN_KHAITAM,
                CATEGORY_BEGINSIGN_KHAITAM,
                CATEGORY_ENDSIGN_KHAITAM);

        String contentCategoryKhaitam = CrawlHelper.fixContent(Crawler_Book.htmlContent);
        List<Category> categories_Khaitam = Parser.getCategoryListFromKhaiTamBook(contentCategoryKhaitam);

        Crawler_Book.parseHTML(CATEGORY_DOMAIN_VANLANG,
                CATEGORY_BEGINSIGN_VANLANG,
                CATEGORY_ENDSIGN_VANLANG);
        String contentCategoryVanlang = CrawlHelper.fixContent(Crawler_Book.htmlContent);
        List<Category> categories_Vanlang = Parser.getCategoryListFromVanLangBook(contentCategoryVanlang);

        System.out.println(contentCategoryKhaitam);
        System.out.println(contentCategoryVanlang);


        System.out.println(categories_Khaitam.size());
        System.out.println(categories_Vanlang.size());
        for (int i = 0; i < 0; i ++) {
            System.out.println(categories_Khaitam.get(i).getCategory_link());
        }
//        String[] categoryKeys = {
//                CATEGORY_KEY_VIETNAM,
//                CATEGORY_KEY_THEGIOI,
//                CATEGORY_KEY_THIEUNHI,
//                CATEGORY_KEY_NGOAINGU
//        };
//        String[] category = {
//                CATEGORY_NAME_VIETNAM,
//                CATEGORY_NAME_THEGIOI,
//                CATEGORY_NAME_THIEUNHI
//        };
//
//        String[] endPointsKhaiTam = {
//                ENDPOINT_VIETNAM_KHAITAM,
//                ENDPOINT_THEGIOI_KHAITAM
//        };
//
//        String[] endPointsVanLang = {
//                ENDPOINT_VIETNAM_VANLANG,
//                ENDPOINT_THEGIOI_VANLANG
//        };


        for (int j = 0; j < 4; j++) {

            Crawler_Book.parseHTML_getPageCount(categories_Khaitam.get(j).getCategory_link());
            Crawler_Book.parseHTML_getPageCount(categories_Vanlang.get(j).getCategory_link());

            int pageCount_Khaitam = Crawler_Book.pageCount_KhaiTam;
            int pageCount_Vanlang = Crawler_Book.pageCount_VangLang;

            for (int i = 1; i <= Math.max(pageCount_Khaitam, pageCount_Vanlang) && isCrawling; i++) {
                if (i <= pageCount_Khaitam) {
                    String url = categories_Khaitam.get(j).getCategory_link() + "?pagenumber=" + i;

                    Crawler_Book.parseHTML(url,
                            KhaiTamConstants.KHAITAM_BEGINSIGN,
                            KhaiTamConstants.KHAITAM_ENDSIGN);

                    String content = CrawlHelper.fixContent(Crawler_Book.htmlContent);

                    List<Product> products = Parser.getProductListFromKhaiTamBook(content, categories_Khaitam.get(j).getCategoryKey(), categories_Khaitam.get(j).getCategoryName());
                    saveProducts(products);
                    System.out.println("Saved " + products.size() + " products from Khai Tam with " + categories_Khaitam.get(j).getCategoryName());
                }
                if (i <= pageCount_Vanlang) {
                    String url = categories_Vanlang.get(j).getCategory_link() +
                            "?pagesize=" + VanLangConstants.VANLANG_PAGESIZE +
                            "&pagenumber=" + i;

                    Crawler_Book.parseHTML(url,
                            VanLangConstants.VANLANG_BEGINSIGN,
                            VanLangConstants.VANLANG_ENDSIGN);

                    String content = CrawlHelper.fixContent(Crawler_Book.htmlContent);

                    List<Product> products = Parser.getProductListFromVanLangBook(content, categories_Vanlang.get(j).getCategoryKey(), categories_Vanlang.get(j).getCategoryName());
                    saveProducts(products);
                    System.out.println("Saved " + products.size() + " products from Van Lang with " + categories_Vanlang.get(j).getCategoryName());
                }

            }

        }


    }

    private void saveProducts(List<Product> products) {
        for (Product product : products) {
            if (productRepository.countByCode(product.getCode()) == 0) {
                productRepository.saveAndFlush(product);
            }
        }
    }

    public void crawlData(boolean status) {
        if (status) {
            isCrawling = true;
            System.out.println("Start Crawling Data...");

            if (!isCrawling) {
                return;
            }
            crawlProduct();

            isCrawling = false;
            System.out.println("Done Crawling Data");
        }
        if (!status) {
            isCrawling = false;//stop
            System.out.println("Stopped Crawling Data");
        }
    }
}
