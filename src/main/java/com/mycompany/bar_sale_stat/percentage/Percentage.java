package com.mycompany.bar_sale_stat.percentage;

import javax.persistence.*;

@Entity
public class Percentage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(length = 50, nullable = false)
    private Double PercentageVolume;



    public Percentage() {
    }

    public Percentage(Integer id, Double percentageVolume) {
        Id = id;
        PercentageVolume = percentageVolume;
    }

    public Double getPercentageVolume() {
        return PercentageVolume;
    }

    public void setPercentageVolume(Double percentageVolume) {
        PercentageVolume = percentageVolume;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    @Override
    public String toString() {
        return String.valueOf(PercentageVolume);
    }
}
