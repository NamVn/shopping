package com.namvn.shopping.persistence.model;

public class ProductInfo {
    private String productId;
    private String name;

    private float priceNew;
    private float  prices;

    public ProductInfo(String productId, String name, float priceNew, float prices) {
        this.productId = productId;
        this.name = name;
        this.priceNew = priceNew;
        this.prices = prices;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPriceNew() {
        return priceNew;
    }

    public void setPriceNew(float priceNew) {
        this.priceNew = priceNew;
    }

    public float getPrices() {
        return prices;
    }

    public void setPrices(float prices) {
        this.prices = prices;
    }
}
