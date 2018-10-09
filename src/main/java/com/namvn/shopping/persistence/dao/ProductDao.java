package com.namvn.shopping.persistence.dao;

import com.namvn.shopping.pagination.PagingResult;
import com.namvn.shopping.persistence.entity.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;

public interface ProductDao {
    Product getProductById(String productId);

    void deleteProduct(String productId);

    void addProduct(Product product);

    void editProduct(Product product);

    Query<Product> queryOrderedPrice(Session session, String query);
    Query<Product> queryBetweenPrice(Session session,float min,float max);
    Query<Product> queryByDetail(Session session, String query);
    PagingResult<Product> getQueryOrderedPrice(int page, int limit, String query);
    //PagingResult<Product> getBetweenPrice(int page, int limit, String query);
    PagingResult<Product> getQueryByDetail(int page, int limit, String query);
}
