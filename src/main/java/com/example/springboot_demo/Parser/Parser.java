package com.example.springboot_demo.Parser;

import com.example.springboot_demo.constant.CategoryConstants;
import com.example.springboot_demo.constant.KhaiTamConstants;
import com.example.springboot_demo.constant.VanLangConstants;
import com.example.springboot_demo.jaxb.product.Product;
import com.example.springboot_demo.jaxb.product.category.Category;
import com.example.springboot_demo.utils.SchemaUtils;
import com.example.springboot_demo.utils.XmlUtils;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static final String PRODUCT_SCHEMA_FILE_PATH = "static/schema/product.xsd";


    public static List<Product> getProductListFromKhaiTamBook(String htmlContent, String categoryKey, String category) {
        XMLStreamReader reader = null;

        boolean foundLink = false;
        boolean foundImage = false;
        boolean foundName = false;
        boolean foundPrice = false;
        boolean foundAuthor = false;

        List<Product> products = new ArrayList<>();

        try {
            reader = XmlUtils.parseContentToCursor(htmlContent);
            Product product = null;


            while (reader.hasNext()) {
                if (!foundLink && !foundImage && !foundName && !foundPrice && !foundAuthor) {
                    product = new Product();
                }

                int cursor = reader.next();
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    String tagName = reader.getLocalName();

                    if (tagName.equals("a")
                            && XmlUtils.getNodeValue(reader, tagName, "", "class") != null) {
                        if (!foundLink) {
                            String link = XmlUtils.getNodeValue(reader, tagName, "", "href");
                            product.setProductLink(link);
                            foundLink = true;

//                             //get more images
//                            Crawler.parseHTML(link,
//                                    RAYBANSTORE_DETAIL_BEGINSIGN,
//                                    RAYBANSTORE_DETAIL_ENDSIGN);
//                            String content = CrawlHelper.fixContent(Crawler.htmlContent);
//                            Images images = new Images();
//                            images.setImageLinks(new List<String>() {
//                            });
////                            images.setImageLinks(getProductImagesFromRayBanStore(content));
//                            product.setImageLinks(images);


                            if (!foundName) {
                                String name = XmlUtils.getNodeValue(reader, tagName, "", "title").trim();
                                product.setName(ParserHelper.capitailizeWord(name));

                                foundName = true;
                            }
                        }

                    } else if (tagName.equals("a")
                            && XmlUtils.getNodeValue(reader, tagName, "", "class") == null
                            && XmlUtils.getNodeValue(reader, tagName, "", "target") == null) {
                        String author = XmlUtils.getTextContent(reader, tagName).trim();
                        product.setAuthor(author);
                        foundAuthor = true;

                    } else if (tagName.equals("img")) {
                        if (!foundImage) {
                            String img = XmlUtils.getNodeValue(reader, tagName, "", "src");
                            product.setAvatarLink("https://www.sachkhaitam.com" + img);
                            product.setImageLinks("https://www.sachkhaitam.com" + img);
                            String code = ParserHelper.generateKhaiTamProductCode(img);
                            product.setCode(code);
                            product.setModel("");

//                            product.setImageLinks();
                            foundImage = true;
                        }
                    } else if (
                            tagName.equals("span")
                                    && XmlUtils.getNodeValue(reader, tagName, "", "class") != null
                                    && XmlUtils.getNodeValue(reader, tagName, "", "class").equals("price-new")
                    ) {

                        if (!foundPrice) {
                            String priceString = XmlUtils.getTextContent(reader, tagName).trim();
                            double price = Double.parseDouble(priceString.substring(0, priceString.length() - 1).replaceAll("\\.", ""));
                            product.setPrice(price);
                            foundPrice = true;
                        }
                    }
                }

                if (cursor == XMLStreamConstants.END_ELEMENT) {


                    String tagName = reader.getLocalName();

                    if (tagName.equals("button")) {
                        if (foundLink && foundImage && foundName && foundPrice && foundAuthor) {
                            product.setCategory(category);
                            product.setCategoryKey(categoryKey);
                            product.setSiteKey(KhaiTamConstants.KHAITAM_SITEKEY);
                            try {
                                product.setId(1);
                                SchemaUtils.validateBySchema(product, PRODUCT_SCHEMA_FILE_PATH);
                                product.setId(0);
                                products.add(product);
                            } catch (Exception e) {
                                System.out.print("Skipped product from '" + product.getProductLink() + "'.");
                            }
                        }
                        foundLink = false;
                        foundImage = false;
                        foundName = false;
                        foundPrice = false;
                        foundAuthor = false;
                    }
                }


            }
        } catch (Exception ex) {
            ex.getMessage();
        }

        return products;
    }

    public static List<Product> getProductListFromVanLangBook(String htmlContent, String categoryKey, String category) {
        XMLStreamReader reader = null;

        boolean foundLink = false;
        boolean foundImage = false;
        boolean foundName = false;
        boolean foundPrice = false;
        boolean foundAuthor = false;

        List<Product> products = new ArrayList<>();

        try {
            reader = XmlUtils.parseContentToCursor(htmlContent);
            Product product = null;


            while (reader.hasNext()) {
                if (!foundLink && !foundImage && !foundName && !foundPrice && !foundAuthor) {
                    product = new Product();
                }


                int cursor = reader.next();
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    String tagName = reader.getLocalName();

                    if (tagName.equals("a")
                            && XmlUtils.getNodeValue(reader, tagName, "", "class") != null) {
                        if (!foundLink) {
                            String link = XmlUtils.getNodeValue(reader, tagName, "", "href");
                            product.setProductLink(link);
                            foundLink = true;

                            if (!foundName) {
                                String name = XmlUtils.getNodeValue(reader, tagName, "", "title").trim();
                                product.setName(ParserHelper.capitailizeWord(name));
                                foundName = true;
                            }
                        }

                    } else if (tagName.equals("div")
                            && XmlUtils.getNodeValue(reader, tagName, "", "class").equals("author")) {
                        String author = XmlUtils.getTextContent(reader, tagName).trim();
                        product.setAuthor(author);
                        foundAuthor = true;

                    } else if (tagName.equals("img")) {
                        if (!foundImage) {
                            String img = XmlUtils.getNodeValue(reader, tagName, "", "data-original");
                            product.setAvatarLink("https://online.vanlang.vn" + img);
                            product.setImageLinks("https://online.vanlang.vn" + img);
                            String code = ParserHelper.generateVanLangProductCode(img);
                            product.setCode(code);
                            product.setModel("");


//                            product.setImageLinks();
                            foundImage = true;
                        }
                    } else if (
                            tagName.equals("div")
                                    && XmlUtils.getNodeValue(reader, tagName, "", "class").equals("new")
                    ) {
                        if (!foundPrice) {
                            String priceString = XmlUtils.getTextContent(reader, tagName).trim();
                            double price = Double.parseDouble(priceString.substring(0, priceString.length() - 1).replaceAll("\\.", ""));
                            product.setPrice(price);
                            foundPrice = true;
                        }
                    }
                }


                if (cursor == XMLStreamConstants.END_ELEMENT) {
                    String tagName = reader.getLocalName();
                    if (tagName.equals("span")) {
                        if (foundLink && foundImage && foundName && foundPrice && foundAuthor) {
                            product.setCategory(category);
                            product.setCategoryKey(categoryKey);
                            product.setSiteKey(VanLangConstants.VANLANG_SITEKEY);
                            try {
                                product.setId(1);
                                SchemaUtils.validateBySchema(product, PRODUCT_SCHEMA_FILE_PATH);
                                product.setId(0);
                                products.add(product);
                            } catch (Exception e) {
                                System.out.print("Skipped product from '" + product.getProductLink() + "'.");
                            }
                        }
                        foundLink = false;
                        foundImage = false;
                        foundName = false;
                        foundPrice = false;
                        foundAuthor = false;
                    }
                }
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        return products;
    }

    public static List<Category> getCategoryListFromKhaiTamBook(String htmlContent) {
        XMLStreamReader reader = null;

        boolean foundCategory = false;


        List<Category> categories = new ArrayList<>();

        try {
            reader = XmlUtils.parseContentToCursor(htmlContent);
            Category category = null;

            while (reader.hasNext()) {
                if (!foundCategory) {
                    category = new Category();
                }

                int cursor = reader.next();

                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    String tagName = reader.getLocalName();
                    if (tagName.equals("a")) {
                        String linkCategory = XmlUtils.getNodeValue(reader, tagName, "", "href");
                        String nameCategory = XmlUtils.getTextContent(reader, tagName);

                        if (nameCategory.contains("Văn Học Việt Nam")) {
                            category.setCategoryKey(CategoryConstants.CATEGORY_KEY_VIETNAM);
                            category.setCategoryName(CategoryConstants.CATEGORY_NAME_VIETNAM);
                            category.setCategory_link(linkCategory);
                            foundCategory = true;

                        } else if (nameCategory.contains("Văn Học Thế Giới")) {
                            category.setCategoryKey(CategoryConstants.CATEGORY_KEY_THEGIOI);
                            category.setCategoryName(CategoryConstants.CATEGORY_NAME_THEGIOI);
                            category.setCategory_link(linkCategory);
                            foundCategory = true;

                        } else if (nameCategory.contains("Thiếu Nhi")) {
                            category.setCategoryKey(CategoryConstants.CATEGORY_KEY_THIEUNHI);
                            category.setCategoryName(CategoryConstants.CATEGORY_NAME_THIEUNHI);
                            category.setCategory_link(linkCategory);
                            foundCategory = true;

                        } else if (nameCategory.contains("Ngoại Ngữ")) {
                            category.setCategoryKey(CategoryConstants.CATEGORY_KEY_NGOAINGU);
                            category.setCategoryName(CategoryConstants.CATEGORY_NAME_NGOAINGU);
                            category.setCategory_link(linkCategory);
                            foundCategory = true;

                        }
                    }
                }

                if (cursor == XMLStreamConstants.END_ELEMENT) {
                    String tagName = reader.getLocalName();
                    if (tagName.equals("li")) {
                        if (foundCategory) {
                            categories.add(category);
                            foundCategory = false;
                        }
                    }
                }
            }
        } catch (XMLStreamException ex) {
            System.out.println("Error parsing category: " + ex.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (XMLStreamException ex) {
                System.out.println("Error parsing category: " + ex.getMessage());
            }
        }
        return categories;

    }

    public static List<Category> getCategoryListFromVanLangBook(String htmlContent) {
        XMLStreamReader reader = null;

        boolean foundCategory = false;


        List<Category> categories = new ArrayList<>();

        try {
            reader = XmlUtils.parseContentToCursor(htmlContent);
            Category category = null;

            while (reader.hasNext()) {

                if (!foundCategory) {
                    category = new Category();
                }

                int cursor = reader.next();

                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    String tagName = reader.getLocalName();
                    if (tagName.equals("a")){
                        String linkCategory = XmlUtils.getNodeValue(reader, tagName, "", "href");
                        String nameCategory = XmlUtils.getTextContent(reader, tagName);

                        if (nameCategory.contains("Sách văn học Việt Nam")) {
                            System.out.println(foundCategory);
                            category.setCategoryKey(CategoryConstants.CATEGORY_KEY_VIETNAM);
                            category.setCategoryName(CategoryConstants.CATEGORY_NAME_VIETNAM);
                            category.setCategory_link(linkCategory);
                            System.out.println(nameCategory);
                            foundCategory = true;


                        } else if (nameCategory.contains("Sách văn học nước ngoài")) {
                            System.out.println(foundCategory);
                            category.setCategoryKey(CategoryConstants.CATEGORY_KEY_THEGIOI);
                            category.setCategoryName(CategoryConstants.CATEGORY_NAME_THEGIOI);
                            category.setCategory_link(linkCategory);
                            System.out.println(nameCategory);
                            foundCategory = true;


                        } else if (nameCategory.contains("Sách thiếu nhi")) {
                            System.out.println(foundCategory);
                            category.setCategoryKey(CategoryConstants.CATEGORY_KEY_THIEUNHI);
                            category.setCategoryName(CategoryConstants.CATEGORY_NAME_THIEUNHI);
                            category.setCategory_link(linkCategory);
                            foundCategory = true;


                        } else if (nameCategory.contains("Sách tin học - ngoại ngữ")) {
                            System.out.println(foundCategory);
                            category.setCategoryKey(CategoryConstants.CATEGORY_KEY_NGOAINGU);
                            category.setCategoryName(CategoryConstants.CATEGORY_NAME_NGOAINGU);
                            category.setCategory_link(linkCategory);
                            foundCategory = true;


                        }
                    }
                }

                if (cursor == XMLStreamConstants.END_ELEMENT) {
                    String tagName = reader.getLocalName();

                    if (tagName.equals("div")) {
                        if (foundCategory) {
                            categories.add(category);
                            foundCategory = false;
                        }
                    }
                }
            }
        } catch (XMLStreamException ex) {
            System.out.println("Error parsing category: " + ex.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (XMLStreamException ex) {
                System.out.println("Error parsing category: " + ex.getMessage());
            }
        }
        return categories;

    }

    public static int getPageCountFromKhaiTamBook(String htmlContent) {
        XMLStreamReader reader = null;

        boolean foundCount = false;
        int pageCount = 0;

        try {
            reader = XmlUtils.parseContentToCursor(htmlContent);


            while (reader.hasNext()) {
                if (!foundCount) {
                    pageCount = 0;
                }

                int cursor = reader.next();
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    String tagName = reader.getLocalName();

                    if (tagName.equals("a")
                            && XmlUtils.getNodeValue(reader, tagName, "", "class").equals("ModulePager LastPage")) {
                        if (!foundCount) {
                            String tmp = XmlUtils.getNodeValue(reader, tagName, "", "href");
                            pageCount = Integer.parseInt(tmp.substring(56));
                            return pageCount;
                        }
                    }
                }


                if (cursor == XMLStreamConstants.END_ELEMENT) {
                    String tagName = reader.getLocalName();
                    if (tagName.equals("li")) {
                        foundCount = false;
                    }
                }
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        return pageCount;
    }
}
