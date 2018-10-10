package com.namvn.shopping.persistence.dao;

import com.namvn.shopping.pagination.PagingResult;
import com.namvn.shopping.persistence.entity.Product;

import com.namvn.shopping.persistence.model.ProductInfo;
import com.namvn.shopping.util.ProductContants;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.*;
import java.util.List;

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
        //builder.or(builder.between(root.get("prices"), min, max),
        criteriaQuery.where(builder.between(root.get("priceNew"), min, max));
        Query<Product> productQuery = session.createQuery(criteriaQuery);
        return productQuery;
    }

    /**
     * todo function to query some property in a table
     * (Ex: group Size, Color, Material, Manufacturer, Made in)(1)
     *
     * @param session
     * @param property is in (1)
     * @param queries  is a properties list. Ex Color: red, white, black ....
     * @see Query<Product>
     */

    @Override
    public Query<Product> queryByProperty(Session session, String property, List<String> queries) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.multiselect(root.get("name"),
                root.get("prices"),
                root.get("priceNew"),
                root.get("province"));
        Expression<String> exp = root.get(property);
        Predicate predicate = exp.in(queries);
        criteriaQuery.where(predicate);
        Query<Product> productQuery = session.createQuery(criteriaQuery);
        return productQuery;
    }

    public Query<Product> queryByColorAndSize(Session session, ProductInfo productInfo) {
        int colorSize = productInfo.getColors().size();
        int sizeSize = productInfo.getSizes().size();
//        int manufacturerSize = productInfo.getManufacturers().size();
//        int materialSize = productInfo.getMaterials().size();
//        int madeInSize = productInfo.getMadeIns().size();

        if (colorSize > 0 && sizeSize > 0) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);
            criteriaQuery.multiselect(root.get("name"),
                    root.get("prices"),
                    root.get("priceNew"),
                    root.get("province"));
            Predicate[] predicates = new Predicate[2];
            predicates[0] = root.get("color").in(productInfo.getColors());
            predicates[1] = root.get("size").in(productInfo.getSizes());
            criteriaQuery.where(builder.and(predicates[0], predicates[1]));
            Query<Product> productQuery = session.createQuery(criteriaQuery);
            return productQuery;
        }
        return null;
    }

    public Query<Product> queryOrderedPriceWithColorSize(Session session, ProductInfo productInfo) {
        int colorSize = productInfo.getColors().size();
        int sizeSize = productInfo.getSizes().size();
        if (colorSize > 0 && sizeSize > 0) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);
            criteriaQuery.multiselect(root.get("name"),
                    root.get("prices"),
                    root.get("priceNew"),
                    root.get("province"));
            Predicate[] predicates = new Predicate[2];
            predicates[0] = root.get("color").in(productInfo.getColors());
            predicates[1] = root.get("size").in(productInfo.getSizes());
            Query<Product> productQuery;
            if (productInfo.getSortType().equals(ProductContants.PRICE_ASC)) {
                criteriaQuery.where(builder.and(predicates[0], predicates[1])).orderBy(builder.asc(root.get("priceNew")));
                productQuery = session.createQuery(criteriaQuery);
                return productQuery;
            } else if (productInfo.getSortType().equals(ProductContants.PRICE_DESC)) {
                criteriaQuery.where(builder.and(predicates[0], predicates[1])).orderBy(builder.desc(root.get("priceNew")));
                productQuery = session.createQuery(criteriaQuery);
                return productQuery;
            }
        }
        return null;
    }

    public Query<Product> queryColorSizeManufacturer(Session session, ProductInfo productInfo) {
        int colorSize = productInfo.getColors().size();
        int sizeSize = productInfo.getSizes().size();
        int manufacturerSize = productInfo.getManufacturers().size();
        if (manufacturerSize > 0 && colorSize > 0 && sizeSize > 0) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);
            criteriaQuery.multiselect(root.get("name"),
                    root.get("prices"),
                    root.get("priceNew"),
                    root.get("province"));
            Predicate[] predicates = new Predicate[3];
            predicates[0] = root.get("color").in(productInfo.getColors());
            predicates[1] = root.get("size").in(productInfo.getSizes());
            predicates[2] = root.get("manufacturer").in(productInfo.getManufacturers());
            criteriaQuery.where(builder.and(predicates[0], predicates[1], predicates[2]));
            Query<Product> productQuery = session.createQuery(criteriaQuery);
            return productQuery;
        }
        return null;
    }

    public Query<Product> queryOrderdPriceColorSizeManufacturer(Session session, ProductInfo productInfo) {
        int colorSize = productInfo.getColors().size();
        int sizeSize = productInfo.getSizes().size();
        int manufacturerSize = productInfo.getManufacturers().size();
        if (manufacturerSize > 0 && colorSize > 0 && sizeSize > 0) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);
            criteriaQuery.multiselect(root.get("name"),
                    root.get("prices"),
                    root.get("priceNew"),
                    root.get("province"));
            Predicate[] predicates = new Predicate[3];
            predicates[0] = root.get("color").in(productInfo.getColors());
            predicates[1] = root.get("size").in(productInfo.getSizes());
            predicates[2] = root.get("manufacturer").in(productInfo.getManufacturers());
            Query<Product> productQuery;
            if (productInfo.getSortType().equals(ProductContants.PRICE_ASC)) {
                criteriaQuery.where(builder.and(predicates[0], predicates[1], predicates[2])).orderBy(builder.asc(root.get("priceNew")));
                productQuery = session.createQuery(criteriaQuery);
                return productQuery;
            } else if (productInfo.getSortType().equals(ProductContants.PRICE_DESC)) {
                criteriaQuery.where(builder.and(predicates[0], predicates[1], predicates[2])).orderBy(builder.desc(root.get("priceNew")));
                productQuery = session.createQuery(criteriaQuery);
                return productQuery;
            }
        }
        return null;
    }

    @Override
    public PagingResult<Product> getQueryOrderedPrice(int page, int limit, String query) {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> productQuery = queryOrderedPrice(session, query);
        return new PagingResult<Product>(productQuery, page, limit);
    }

    @Override
    public PagingResult<Product> getBetweenPrice(int page, int limit, float min, float max) {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> productQuery = queryBetweenPrice(session, min, max);
        return new PagingResult<Product>(productQuery, page, limit);
    }

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
