package com.namvn.shopping.persistence.dao;

import com.namvn.shopping.pagination.PagingResult;
import com.namvn.shopping.persistence.entity.Product;
import com.namvn.shopping.persistence.model.ProductInfo;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public interface ProductDao {
    Product getProductById(String productId);

    void deleteProduct(String productId);

    void addProduct(Product product);

    void editProduct(Product product);

    Query<Product> queryOrderedPrice(Session session, String query);
    Query<Product> queryBetweenPrice(Session session,float min,float max);
    Query<Product> queryByProperty(Session session,String property, List<String> queries);
     Query<Product> queryByColorAndSize(Session session, ProductInfo productInfo);
     Query<Product> queryOrderedPriceWithColorSize(Session session, ProductInfo productInfo);
//    Query<Product> queryBySize(Session session, String query);
//    Query<Product> queryByManufacturer(Session session, String query);
//    Query<Product> queryByMaterial(Session session, String query);
//    Query<Product> queryByMadeIn(Session session, String query);
//    Query<Product> queryByDetail(Session session, String query);
    PagingResult<Product> getQueryOrderedPrice(int page, int limit, String query);
    PagingResult<Product> getBetweenPrice(int page, int limit,float min, float max);
    PagingResult<Product> getQueryByDetail(int page, int limit, String query);
}
