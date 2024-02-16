package com.sql.jdbc;

import com.sql.jdbc.entity.Product;
import com.sql.jdbc.entity.User;
import com.sql.jdbc.entity.UserDetail;
import com.sql.jdbc.service.*;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        /* user class */
        UserService userService = new UserService();

        userService.add(new User(41,"Boh","Kalytyn","Uzhhorod"));
        List<User> users = userService.getAll();
        User userDB = userService.getById(41);
        userService.update(new User("Bohdan","Kalytyn","Uzhorod"),41);
        userService.delete(40);

        /* user_details class */
        UserDetailsService userDetailsService = new UserDetailsService();
        userDetailsService.add(new UserDetail(41,23,"0988345001","CYBER-PLAYER"));
        userDetailsService.getAll();
        UserDetail userDetail = userDetailsService.getById(22);
        userDetailsService.update(new UserDetail(25, "0982370000", "actor"), 41);
        userDetailsService.delete(20);

        /* products class */
        ProductService productService = new ProductService();
        productService.add(new Product("yogurt",20));
        productService.getAll();
        Product product = productService.getById(13);
        productService.update(new Product("cookie",5000),13);
        productService.delete(13);

        /* shoppingCart class */
        ShoppingCartService shoppingCartService = new ShoppingCartService();
        shoppingCartService.addProduct(1,1);
        shoppingCartService.deleteByProduct(1,1);
        shoppingCartService.getAllProductsByUser(1);
        shoppingCartService.deleteAllProductsByUser(1);

        OrderService orderService = new OrderService();
        orderService.add(2);
        orderService.getAllById(1);
        orderService.getAll();

        orderService.closeConnection();
        orderService.closeConnection();

    }
}

