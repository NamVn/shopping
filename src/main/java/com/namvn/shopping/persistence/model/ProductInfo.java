package com.namvn.shopping.persistence.model;

public class ProductInfo {
    private String productId;
    private String name;

    private String priceNew;
    private String  prices;

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
}
