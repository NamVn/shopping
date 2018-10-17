package com.namvn.shopping.persistence.dao;

import com.namvn.shopping.persistence.entity.Cart;

import java.io.IOException;

public interface CartDao {
    Cart getCartByCartId(Long cartId);

    Cart validate(Long cartId) throws IOException;

    void update(Cart cart);
}
