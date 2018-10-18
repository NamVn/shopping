package com.namvn.shopping.persistence.entity;



import javax.persistence.*;

@Entity
@Table(name = "cartitem")
public class CartItem {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartItemId;
    private int quanlity;
    private double price;
    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    public CartItem() {
    }


    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }
}
