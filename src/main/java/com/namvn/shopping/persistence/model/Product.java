package com.namvn.shopping.persistence.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String detail;

    private float prices;
    @NotNull
    private String image;
    private float priceNew;
    @NotNull
    private Date date;
    private int status;

    public Product() {
    }

    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public float getPrices() {
        return prices;
    }

    public void setPrices(float prices) {
        this.prices = prices;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPriceNew() {
        return priceNew;
    }

    public void setPriceNew(float priceNew) {
        this.priceNew = priceNew;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
