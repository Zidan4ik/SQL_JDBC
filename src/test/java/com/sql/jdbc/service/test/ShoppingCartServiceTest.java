package com.sql.jdbc.service.test;

import com.sql.jdbc.service.ShoppingCartService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ShoppingCartServiceTest {
    private ShoppingCartService shoppingCartService;
    @BeforeEach
    public void createObject(){
        shoppingCartService = new ShoppingCartService();
    }
    @Test
    void addProduct() {
        int userId = 6;
        int productId = 2;
        List<Integer> productsByUser = shoppingCartService.getAllProductsByUser(userId);
        Assert.assertEquals(0,productsByUser.size());
        shoppingCartService.addProduct(userId,productId);
        productsByUser = shoppingCartService.getAllProductsByUser(userId);
        Assert.assertNotEquals(0,productsByUser.size());

    }

    @Test
    void deleteByProduct() {
        int userId = 1;
        int productId = 3;
        int sizeBefore = 0;
        int sizeAfter = 0;
        List<Integer> productsByUserBefore = shoppingCartService.getAllProductsByUser(userId);
        shoppingCartService.deleteByProduct(userId,productId);
        List<Integer> productsByUserAfter = shoppingCartService.getAllProductsByUser(userId);

        for (int i = 0; i < productsByUserBefore.size(); i++) {
            if(productsByUserBefore.get(i).equals(productId))
                sizeBefore++;
        }
        for (int i = 0; i < productsByUserAfter.size(); i++) {
            if(productsByUserAfter.get(i).equals(productId))
            sizeAfter++;
        }

        Assert.assertTrue("розмір колекції збільшився, а мав зменшитись",sizeBefore>sizeAfter);

    }

    @Test
    void getAllProductsByUser() {
        List<Integer> productsByUser = shoppingCartService.getAllProductsByUser(1);

        Assert.assertNotNull(productsByUser);
        Assert.assertTrue("розмір колекції не може бути негативним",productsByUser.size()>=0);
    }

    @Test
    void deleteAllProductsByUser() {
        int userId = 1;
        List<Integer> productByUser = shoppingCartService.getAllProductsByUser(userId);
        Assert.assertNotEquals(0,productByUser.size());
        shoppingCartService.deleteAllProductsByUser(userId);
        productByUser = shoppingCartService.getAllProductsByUser(userId);
        Assert.assertEquals(0,productByUser.size());

    }
}