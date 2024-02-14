package com.sql.jdbc.entity;

public class ShoppingCart {
    private int id;
    private ShoppingCart idUserCart;
    private Product idProduct;

    public ShoppingCart() {
    }

    public ShoppingCart(int id, ShoppingCart idUserCart, Product idProduct) {
        this.id = id;
        this.idUserCart = idUserCart;
        this.idProduct = idProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ShoppingCart getIdUserCart() {
        return idUserCart;
    }

    public void setIdUserCart(ShoppingCart idUserCart) {
        this.idUserCart = idUserCart;
    }

    public Product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Product idProduct) {
        this.idProduct = idProduct;
    }
}
