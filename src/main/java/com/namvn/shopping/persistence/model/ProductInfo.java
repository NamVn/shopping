package com.namvn.shopping.persistence.model;

public class ProductInfo {

    private String name;
    private float prices;

    public ProductInfo(String name, float prices) {
        this.name = name;
        this.prices = prices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrices() {
        return prices;
    }

    public void setPrices(float prices) {
        this.prices = prices;
    }
}
