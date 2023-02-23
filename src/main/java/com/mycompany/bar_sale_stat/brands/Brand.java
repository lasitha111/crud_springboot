package com.mycompany.bar_sale_stat.brands;

import com.mycompany.bar_sale_stat.category.Category;
import com.mycompany.bar_sale_stat.percentage.Percentage;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Brand {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String brand_name;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "brand_percentage",
            joinColumns = @JoinColumn(name = "brand_id"),
            inverseJoinColumns = @JoinColumn(name = "percentage_id")
    )
//    @Column(nullable = false)
    private Set<Percentage> percentages = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Brand() {
    }


    public Brand(Integer id, String brand_name, Set<Percentage> percentages, Category category) {
        this.id = id;
        this.brand_name = brand_name;
        this.percentages = percentages;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public Set<Percentage> getPercentages() {
        return percentages;
    }

    public void setPercentages(Set<Percentage> percentages) {
        this.percentages = percentages;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
