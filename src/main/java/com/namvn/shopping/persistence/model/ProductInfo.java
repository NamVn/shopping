package com.namvn.shopping.persistence.model;

import java.util.List;

public class ProductInfo {
//    private String color;
//    private String size;
//    private String manufacturer;
//    private String material;
//    private String madeIn;
//    private String province;
    private String sortType;
    private float minPrice;
    private float maxPrice;
    List<String> colors;
    List<String> sizes;
    List<String> manufacturers;
    List<String> materials;
    List<String> madeIns;



    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public List<String> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<String> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }

    public List<String> getMadeIns() {
        return madeIns;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public void setMadeIns(List<String> madeIns) {
        this.madeIns = madeIns;
    }
}
