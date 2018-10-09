package com.namvn.shopping.persistence.dao;

import com.namvn.shopping.pagination.PagingResult;
import com.namvn.shopping.persistence.entity.Product;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ProductDaoImpl implements ProductDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Query<Product> queryOrderedPrice(Session session, String query) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.multiselect(root.get("name"),
                root.get("prices"),
                root.get("priceNew"),
                root.get("province")).orderBy(builder.asc(root.get(query)));
        Query<Product> productQuery = session.createQuery(criteriaQuery);
        return productQuery;
    }

    @Override
    public Query<Product> queryBetweenPrice(Session session, float min, float max) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.multiselect(root.get("name"),
                root.get("prices"),
                root.get("priceNew"),
                root.get("province"));
        criteriaQuery.where(builder.or(builder.between(root.get("prices"), min, max),
                builder.between(root.get("priceNew"), min, max)));
        Query<Product> productQuery = session.createQuery(criteriaQuery);
        return productQuery;
    }

    @Override
    public Query<Product> queryByDetail(Session session, String query) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.multiselect(root.get("name"),
                root.get("prices"),
                root.get("priceNew"),
                root.get("province")).where(builder.like(root.get(query), '%' + query + '%'));
        Query<Product> productQuery = session.createQuery(criteriaQuery);
        return productQuery;
    }

    @Override
    public PagingResult<Product> getQueryOrderedPrice(int page, int limit, String query) {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> productQuery = queryOrderedPrice(session, query);
        return new PagingResult<Product>(productQuery, page, limit);
    }

//    @Override
//    public PagingResult<Product> getBetweenPrice(int page, int limit, String query) {
//        Session session = sessionFactory.getCurrentSession();
//        Query<Product> productQuery = queryBetweenPrice(session, query);
//        return new PagingResult<Product>(productQuery, page, limit);
//    }

    @Override
    public PagingResult<Product> getQueryByDetail(int page, int limit, String query) {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> productQuery = queryOrderedPrice(session, query);
        return new PagingResult<Product>(productQuery, page, limit);
    }

    @Override
    public Product getProductById(String productId) {
        // Reading the records from the table
        Session session = sessionFactory.getCurrentSession();
        // select * from Product where isbn=i
        Product product = session.get(Product.class, productId);

        return product;
    }

    @Override
    public void deleteProduct(String productId) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, productId);
        session.delete(product);
        // close the session
    }

    @Override
    public void addProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);

    }

    @Override
    public void editProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.update(product);
    }
}
