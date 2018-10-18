package com.namvn.shopping.service;

import com.namvn.shopping.persistence.entity.Cart;
import com.namvn.shopping.persistence.entity.User;
import com.namvn.shopping.persistence.entity.UserOrder;
import com.namvn.shopping.persistence.repository.CartDao;
import com.namvn.shopping.persistence.repository.UserOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserOrderServiceImpl implements UserOrderService {
    @Autowired
    private CartDao mCartDao;

    @Autowired
    private UserOrderDao mUserOrderDao;

    @Override
    public void addUserOrder(Long cartId) {
        UserOrder customerOrder = new UserOrder();

        Cart cart = mCartDao.getCartByCartId(cartId);
        // Update CartId for customerOrder - set CartId
        customerOrder.setCart(cart);
        User user = cart.getUser();

        customerOrder.setUser(user);
        customerOrder.setBill(user.getBill());

        mUserOrderDao.addOrder(customerOrder);
    }
}
