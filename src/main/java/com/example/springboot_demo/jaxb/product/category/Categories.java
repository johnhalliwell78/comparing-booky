package com.example.springboot_demo.jaxb.product.category;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "categories"
})
@XmlRootElement(name = "categories")
public class Categories {

    @XmlElement(name = "category", required = true)
    private List<Category> categories;

    public List<Category> getCategories() {
        if (categories == null) {
            categories = new ArrayList<Category>();
        }
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
