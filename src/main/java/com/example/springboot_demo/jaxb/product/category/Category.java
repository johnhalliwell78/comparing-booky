
package com.example.springboot_demo.jaxb.product.category;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Category", propOrder = {
        "key",
        "name",
        "category_link"
})
@XmlRootElement(name = "category")
@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "category_key", nullable = false, length = 50)
    @XmlElement(required = true)
    protected String key;

    @Basic
    @Column(name = "category", nullable = false, length = 500)
    @XmlElement(required = true)
    protected String name;

    @Basic
    @Column(name = "category_link", nullable = true, length = 15000)
    @XmlElement(required = true)
    protected String category_link;

    public String getCategoryKey() {
        return key;
    }

    public void setCategoryKey(String value) {
        this.key = value;
    }

    public String getCategoryName() {
        return name;
    }

    public void setCategoryName(String value) {
        this.name = value;
    }

    public String getCategory_link() {
        return category_link;
    }

    public void setCategory_link(String category_link) {
        this.category_link = category_link;
    }
}
