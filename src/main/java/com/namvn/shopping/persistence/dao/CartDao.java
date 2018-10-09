package com.namvn.shopping.persistence.dao;

import com.namvn.shopping.persistence.entity.Cart;

import java.io.IOException;

public interface CartDao {
    Cart getCartByCartId(String cartId);

    Cart validate(String cartId) throws IOException;

    void update(Cart cart);
}
