package com.namvn.shopping.persistence.dao;

import com.namvn.shopping.persistence.entity.Catergory;

public interface CatergoryDao {
    void addCatergoryProduct(Catergory catergory);
    //void removeCatergoryProduct(String name);
    void getCatergory(String name);
}
