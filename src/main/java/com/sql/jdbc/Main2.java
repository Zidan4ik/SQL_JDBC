package com.sql.jdbc;

import com.sql.jdbc.entity.User;
import com.sql.jdbc.service.UserDetailsService;
import com.sql.jdbc.service.UserService;

import java.util.List;


public class Main2 {
    public static void main(String[] args) {

        /* user class */
        UserService userService = new UserService();

//        userService.add(new User(27,"Dima","Prosk","SITY"));

        List<User> users = userService.getAll();

//        User userDB = userService.getById(5);

//        userService.update(new User("Maks","Bodnevych","Lviv"),19);

//        userService.delete(18);

        /* user_details class */
        UserDetailsService userDetailsService = new UserDetailsService();
//        userDetailsService.add(new UserDetail(22,22,"0505050050","painter"));
        userDetailsService.getAll();
        userDetailsService.closeConnection();
        userService.closeConnection();
//        UserDetail userDetail = userDetailsService.getById(22);
//        System.out.println(userDetail);
//
//        userDetailsService.update(new UserDetail(22, "0982370000", "actor"), 22);
//        System.out.println(userDetailsService.getById(2));
//        userDetailsService.delete(22);

        /* products class */
//        ProductService productService = new ProductService();

//        productService.add(new Product("yogurt",20));
//        productService.getAll();
//        Product product = productService.getById(13);
//        System.out.println(product);
//        productService.update(new Product("cookie",5000),13);
//        productService.delete(13);

        /* shoppingCart class */
//        shoppingCartService.addProduct(1,1);
//        shoppingCartService.deleteByProduct(1,1);
//        shoppingCartService.getAllProductsByUser(1);
//        shoppingCartService.deleteAllProductsByUser(1);

//        OrderService orderService = new OrderService();
//        orderService.add(1);
//        orderService.getAllById(1);
//        orderService.getAll();
//
//        orderService.closeConnection();
//        orderService.closeConnection();

    }
}

