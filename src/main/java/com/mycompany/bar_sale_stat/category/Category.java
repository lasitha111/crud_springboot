package com.mycompany.bar_sale_stat.category;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(length = 50, unique = true)
    private String Category;


    public Category() {
    }

    public Category(Integer id, String category) {
        Id = id;
        Category = category;

    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

}
