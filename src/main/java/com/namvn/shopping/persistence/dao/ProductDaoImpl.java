package com.namvn.shopping.persistence.dao;

import com.namvn.shopping.pagination.PagingResult;
import com.namvn.shopping.persistence.entity.Product;

import com.namvn.shopping.persistence.model.ProductInfo;
import com.namvn.shopping.util.PreprocessingInput;
import com.namvn.shopping.util.ProductContants;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.*;
import java.util.Map;
import java.util.Set;

public class ProductDaoImpl implements ProductDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void queryPredicatesBetweenPrice(CriteriaQuery<Product> criteriaQuery, CriteriaBuilder builder, Root<Product> root, ProductInfo productInfo, int parameter, Predicate predicates[]) {
        float min_price = productInfo.getMinPrice();
        float max_price = productInfo.getMaxPrice();
        if ((min_price + max_price) != 0) {
            if (parameter == 1) {
                criteriaQuery.where(builder.and(predicates[0], builder.between(root.get(ProductContants.PRICE_NEW), min_price, max_price)));
            } else if (parameter == 2) {
                criteriaQuery.where(builder.and(predicates[0], predicates[1], builder.between(root.get(ProductContants.PRICE_NEW), min_price, max_price)));
            } else if (parameter == 3) {
                criteriaQuery.where(builder.and(predicates[0], predicates[1], predicates[2], builder.between(root.get(ProductContants.PRICE_NEW), min_price, max_price)));
            } else if (parameter == 4) {
                criteriaQuery.where(builder.and(predicates[0], predicates[1], predicates[2], predicates[3], builder.between(root.get(ProductContants.PRICE_NEW), min_price, max_price)));
            } else if (parameter == 5) {
                criteriaQuery.where(builder.and(predicates[0], predicates[1], predicates[2], predicates[3], predicates[4], builder.between(root.get(ProductContants.PRICE_NEW), min_price, max_price)));
            }
        } else {
            if (parameter == 1) {
                criteriaQuery.where(predicates);
            } else if (parameter == 2) {
                criteriaQuery.where(builder.and(predicates[0], predicates[1]));
            } else if (parameter == 3) {
                criteriaQuery.where(builder.and(predicates[0], predicates[1], predicates[2]));
            } else if (parameter == 4) {
                criteriaQuery.where(builder.and(predicates[0], predicates[1], predicates[2], predicates[3]));
            } else if (parameter == 5) {
                criteriaQuery.where(builder.and(predicates[0], predicates[1], predicates[2], predicates[3], predicates[4]));
            }

        }

    }

    public void queryOrderdPredicatesByPrice(CriteriaQuery<Product> criteriaQuery, CriteriaBuilder builder, Root<Product> root, ProductInfo productInfo) {
        if (productInfo.getSortType().equals(ProductContants.PRICE_ASC)) {
            criteriaQuery.orderBy(builder.asc(root.get("priceNew")));
        } else if (productInfo.getSortType().equals(ProductContants.PRICE_DESC)) {
            criteriaQuery.orderBy(builder.desc(root.get("priceNew")));
        }
    }

    @Override
    public Query<Product> queryByPredicates(Session session, ProductInfo productInfo) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.multiselect(root.get("name"),
                root.get("prices"),
                root.get("priceNew"),
                root.get("province"));
        Map predicateMap = new PreprocessingInput().filterPredicate(productInfo);
        int i = 0;
        Query<Product> productQuery = null;
        int mapSize = predicateMap.size();
        if (mapSize == 1) {
            Predicate predicates[] = new Predicate[1];
            Set<String> set = predicateMap.keySet();
            for (String key : set) {
                predicates[0] = root.get(key).in(predicateMap.get(key));
            }
            queryPredicatesBetweenPrice(criteriaQuery, builder, root, productInfo, mapSize, predicates);
            queryOrderdPredicatesByPrice(criteriaQuery, builder, root, productInfo);
            productQuery = session.createQuery(criteriaQuery);
            return productQuery;

        } else if (mapSize == 2) {
            Set<String> set = predicateMap.keySet();
            Predicate predicates[] = new Predicate[2];
            for (Object key : set) {
                predicates[i] = root.get((String) key).in(predicateMap.get(key));
                i++;
            }
            queryPredicatesBetweenPrice(criteriaQuery, builder, root, productInfo, mapSize, predicates);
            queryOrderdPredicatesByPrice(criteriaQuery, builder, root, productInfo);
            productQuery = session.createQuery(criteriaQuery);
            return productQuery;

        } else if (mapSize == 3) {
            Set<String> set = predicateMap.keySet();
            Predicate predicates[] = new Predicate[3];
            for (Object key : set) {
                predicates[i] = root.get((String) key).in(predicateMap.get(key));
                i++;
            }
            queryPredicatesBetweenPrice(criteriaQuery, builder, root, productInfo, mapSize, predicates);
            queryOrderdPredicatesByPrice(criteriaQuery, builder, root, productInfo);
            productQuery = session.createQuery(criteriaQuery);
            return productQuery;
        } else if (mapSize == 4) {
            Set<String> set = predicateMap.keySet();
            Predicate predicates[] = new Predicate[4];
            for (Object key : set) {
                predicates[i] = root.get((String) key).in(predicateMap.get(key));
                i++;
            }
            queryPredicatesBetweenPrice(criteriaQuery, builder, root, productInfo, mapSize, predicates);
            queryOrderdPredicatesByPrice(criteriaQuery, builder, root, productInfo);
            productQuery = session.createQuery(criteriaQuery);
            return productQuery;
        } else if (mapSize == 5) {
            Set<String> set = predicateMap.keySet();
            Predicate predicates[] = new Predicate[5];
            for (Object key : set) {
                predicates[i] = root.get((String) key).in(predicateMap.get(key));
                i++;
            }
            queryPredicatesBetweenPrice(criteriaQuery, builder, root, productInfo, mapSize, predicates);
            queryOrderdPredicatesByPrice(criteriaQuery, builder, root, productInfo);
            productQuery = session.createQuery(criteriaQuery);
            return productQuery;
        }
        return productQuery;
    }

    @Override
    public PagingResult<Product> getQueryByDetail(int page, int limit, ProductInfo productInfo) {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> productQuery = queryByPredicates(session, productInfo);
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
