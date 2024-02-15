package com.sql.jdbc.service;

import com.sql.jdbc.entity.Product;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ProductServiceTest {
    private ProductService productService;
    @BeforeEach
    public void createService(){
        productService = new ProductService();
    }
    @Test
    void add() {
        Product product = new Product(13,"waffle",111);
        productService.add(product);

        Product productFromDB = productService.getById(product.getId());

        Assert.assertNotNull(productFromDB);

        Assert.assertEquals(productFromDB.getId(),product.getId());
        Assert.assertEquals(productFromDB.getName(),product.getName());
        Assert.assertEquals(productFromDB.getPrice(),product.getPrice());

        productService.delete(product.getId());
    }

    @Test
    void getAll() {
        List<Product> products = productService.getAll();

        Assert.assertNotNull(products);
        Assert.assertTrue(products.size()>=0);
    }

    @Test
    void getById() {
        Product product = productService.getById(3);
        Assert.assertNotNull(product);
        Assert.assertEquals(3,product.getId());
    }

    @Test
    void update() {
        int productId = 1;
        Product product = new Product("ice-cream",235);
        productService.update(product,productId);

        Product productFromDB = productService.getById(productId);

        Assert.assertNotNull(productFromDB);
        Assert.assertEquals(productFromDB.getName(),product.getName());
        Assert.assertEquals(productFromDB.getPrice(),product.getPrice());
    }

    @Test
    void delete() {
        int productId = 9;
        Product productBefore = productService.getById(productId);
        Assert.assertNotNull(productBefore);

        productService.delete(productId);

        Product productAfter = productService.getById(productId);
        Assert.assertNull(productAfter);
    }
}