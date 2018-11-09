package com.example.springboot_demo.Crawler;

import com.example.springboot_demo.Parser.Parser;
import com.example.springboot_demo.constant.VanLangConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class Crawler_Book {

    public static String htmlContent = "";
    public static String htmlContent_PageCount_Khaitam = "";
    public static String htmlContent_PageCount_Vanlang = "";
    public static int pageCount_KhaiTam = 0;
    public static int pageCount_VangLang = 0;
    //    public static String book = "https://online.vanlang.vn/nha-sach-online";
    public static String BOOK_KHAI_TAM = "https://www.sachkhaitam.com/";
    public static String BOOK_VAN_LANG = "https://online.vanlang.vn/nha-sach-online/sach-van-hoc/";
    public static String BOOK_VAN_LANG_DOMAIN = "https://online.vanlang.vn/nha-sach-online/sach-van-hoc";
    public static String BOOK_KHAI_TAM_DOMAIN = "https://www.sachkhaitam.com";

    public static void parseHTML_getPageCount(String uri) {
//        htmlContent_PageCount_Vanlang = "";
//        htmlContent_PageCount_Khaitam = "";
//        pageCount_KhaiTam = 0;
//        pageCount_VangLang = 0;

        boolean isInside = false;
        int count = 0;

        try {
            URL url = new URL(uri);
            URLConnection con = url.openConnection();
            con.addRequestProperty("User-agent", "Chrome/61.0.3163.100 (compatible; MSIE 6.0; Windows NT 5.0");

            InputStream is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String inputLine = null;


            while ((inputLine = br.readLine()) != null) {
                if (uri.contains("khaitam")) {
                    if (inputLine.contains("Trang cuối")) {
                        htmlContent_PageCount_Khaitam = "";
                        htmlContent_PageCount_Khaitam = htmlContent_PageCount_Khaitam + "<root>" + inputLine + "</root>";
                        pageCount_KhaiTam = pageCount_KhaiTam + Parser.getPageCountFromKhaiTamBook(htmlContent_PageCount_Khaitam);
                    }
                } else if (uri.contains("vanlang")) {

                    if (inputLine.contains("view-option pull-xs-right")) {
                        if (count == 0) {
                            isInside = true;
                        }
                        count++;
                    }

                    if (inputLine.contains("view-type ajaxlink active")) {
                        isInside = false;
                    }

                    if (isInside) {
                        inputLine = wellformContent(uri, inputLine);
                        htmlContent_PageCount_Vanlang = htmlContent_PageCount_Vanlang + inputLine;
                    }
                }
            }
            is.close();
            if (htmlContent_PageCount_Vanlang != "") {
                String temp = CrawlHelper.fixContent(htmlContent_PageCount_Vanlang).substring(71);
                String temp2 = temp.substring(0, temp.length() - 23);
                pageCount_VangLang = Integer.parseInt(temp2) / VanLangConstants.VANLANG_PAGESIZE;
                htmlContent_PageCount_Vanlang = "";
            }
        } catch (IOException ex) {
            System.out.print("Error crawling HTML content from '" + uri + "': " + ex.getMessage());
        }
    }

    public static void parseHTML(String uri, String beginSign, String endSign) {
//        System.out.println("Crawler - URI: " + uri);
        htmlContent = "";


        boolean isInside = false;
        int count = 0;

        try {
            URL url = new URL(uri);

            URLConnection con = url.openConnection();
            con.addRequestProperty("User-agent", "Chrome/61.0.3163.100 (compatible; MSIE 6.0; Windows NT 5.0");

            InputStream is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String inputLine = null;


            while ((inputLine = br.readLine()) != null) {
                if (inputLine.contains(beginSign)) {
                    if (count == 0) {
                        isInside = true;
                    }
                    count++;
                }

                if (inputLine.contains(endSign)) {
                    isInside = false;
                }

                if (isInside) {
                    inputLine = wellformContent(uri, inputLine);
                    htmlContent = htmlContent + inputLine;
//                    System.out.println("Crawler - inputLine: " + inputLine);
                }

//                if (uri.contains("khaitam")) {
//                    if (inputLine.contains("Trang cuối")) {
//                        htmlContent_PageCount_Khaitam = htmlContent_PageCount_Khaitam + "<root>" + inputLine + "</root>";
//                        System.out.println(Parser.getPageCountFromKhaiTamBook(htmlContent_PageCount_Khaitam));
//                        pageCount_KhaiTam = pageCount_KhaiTam + Parser.getPageCountFromKhaiTamBook(htmlContent_PageCount_Khaitam);
//                        System.out.println(pageCount_KhaiTam);
//                    }
//                }
            }

            is.close();
        } catch (IOException ex) {
            System.out.print("Error crawling HTML content from '" + uri + "': " + ex.getMessage());
        }
    }

    private static String wellformContent(String uri, String inputLine) {


        if (uri == "https://www.sachkhaitam.com") {

            if (inputLine.contains("<span class=\"menu-title\">") && !inputLine.contains("hidden")) {//add missing </a>
                inputLine = "" + inputLine.replace("<span class=\"menu-title\">", "").replace("</span>", "");
                return inputLine;
            }

            if (inputLine.contains("<span class=\"menu-title\">") && inputLine.contains("hidden")) {//add missing </a>
                inputLine = "" + inputLine.replace("<span class=\"menu-title\">", "").replace("</span><span class=\"hidden-xs hidden-sm hidden-md pull-right\"><i class=\"fa fa-angle-right\"></i></span>", "");
                return inputLine;
            }
        } else if (uri == "https://online.vanlang.vn/nha-sach-online") {
            if (inputLine.contains("img")) {
                return inputLine.replace("</a>", "</img></a>");
            }

            if (inputLine.contains("</a><a")) return inputLine.replace("</a><a", "</a><div></div><a");
        } else {
            if (inputLine.contains("<img ")) {//add missing </a>
//                System.out.println(inputLine);
                return inputLine + "</img>";
            }
            if (inputLine.contains("figcaption") || inputLine.contains("figure")) {//remove redundant </a>
                return "";
            }
        }
        return inputLine;
    }

    private static String wellformContent_Categories(String inputLine) {
        return inputLine + "</ul></nav></div></div>";
    }


//    public static void main(String[] args) {
////        Crawler_Book.parseHTML("",
////                "",
////                "");
////        System.out.println(Crawler_Book.htmlContent);
//
//        Crawler_Book.parseHTML("",
//                "",
//                "");
//
//        String content = CrawlHelper.fixContent(Crawler_Book.htmlContent);
//        System.out.println(content);
//
//
//        List<Category> categories = Parser.getCategoryListFromVanLangBook(content);
//        System.out.println(categories.size());
//        for (int i = 0; i < categories.size(); i++) {
//            System.out.println(categories.get(i).getCategoryKey());
//            System.out.println(categories.get(i).getCategoryName());
//            System.out.println(categories.get(i).getCategory_link());
//        }
//
//    }

}


