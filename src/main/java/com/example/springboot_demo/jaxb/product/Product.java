package com.example.springboot_demo.jaxb.product;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Product", propOrder = {
        "id",
        "code",
        "model",
        "name",
        "author",
        "price",
        "avatarLink",
        "productLink",
        "categoryKey",
        "category",
        "siteKey",
        "imageLinks"
})
@XmlRootElement(name = "product")
@Entity
@Table(name = "product")
public class Product {

    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected int id;

    @XmlElement(required = true)
    @Basic
    @Column(name = "product_code", nullable = false, length = 50)
    protected String code;

    @XmlElement(required = true)
    @Basic
    @Column(name = "model", nullable = false, length = 50)
    protected String model;

    @XmlElement(required = true)
    @Basic
    @Column(name = "product_name", nullable = false, length = 500)
    protected String name;

    @XmlElement(required = true)
    @Basic
    @Column(name = "product_author", nullable = false, length = 500)
    protected String author;

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    protected double price;

    @XmlElement(required = true)
    @Basic
    @Column(name = "avatar_link", nullable = false, length = 500)
    protected String avatarLink;

    @XmlElement(required = true)
    @Basic
    @Column(name = "product_link", nullable = false, length = 500)
    protected String productLink;

    @XmlElement(required = true)
    @Basic
    @Column(name = "category_key", nullable = false, length = 50)
    protected String categoryKey;

    @XmlElement(required = true)
    @Basic
    @Column(name = "category", nullable = false, length = 500)
    protected String category;

    @XmlElement(required = true)
    @Basic
    @Column(name = "site_key", nullable = false, length = 50)
    protected String siteKey;

    @XmlElement(required = true)
    @Transient
    protected String imageLinks;

    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        this.code = value;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String value) {
        this.model = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double value) {
        this.price = value;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String value) {
        this.avatarLink = value;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String value) {
        this.productLink = value;
    }

    public String getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(String value) {
        this.categoryKey = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String value) {
        this.category = value;
    }


    public String getSiteKey() {
        return siteKey;
    }

    public void setSiteKey(String value) {
        this.siteKey = value;
    }

    public String getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(String  value) {
        this.imageLinks = value;
    }

}
