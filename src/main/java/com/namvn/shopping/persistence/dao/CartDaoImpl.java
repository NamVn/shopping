package com.namvn.shopping.persistence.dao;

import com.namvn.shopping.persistence.entity.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Repository
@Transactional
public class CartDaoImpl implements CartDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Cart getCartByCartId(String cartId) {
        Session session = sessionFactory.getCurrentSession();
        Cart cart = session.get(Cart.class, cartId);
        return cart;
    }

    @Override
    public Cart validate(String cartId) throws IOException {
        return null;
    }

    @Override
    public void update(Cart cart) {

    }
}
