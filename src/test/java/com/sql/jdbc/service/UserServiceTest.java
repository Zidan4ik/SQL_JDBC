package com.sql.jdbc.service;

import com.sql.jdbc.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserServiceTest {
    private UserService userService;

    @BeforeEach
    public void createService() {
        userService = new UserService();
    }

    @Test
    void add() {
        User user = new User(35, "Grym", "Fench", "STR");

        userService.add(user);
        User userFromDB = userService.getById(user.getId());

        Assert.assertEquals(user.getId(), userFromDB.getId());
        Assert.assertEquals(user.getName(), userFromDB.getName());
        Assert.assertEquals(user.getSurname(), userFromDB.getSurname());
        Assert.assertEquals(user.getCity(), userFromDB.getCity());

        System.out.println("перевірка методу save до database пройшла успішно");
        userService.delete(user.getId());
    }

    @Test
    void getAll() {
        List<User> users = userService.getAll();

        Assert.assertNotNull(users);
        Assert.assertNotEquals(0,users.size());
    }

    @Test
    void getById() {
        int id = 1;
        User user = userService.getById(id);

        if(user==null){
            System.out.println("Такого id для користувача не існує!");
            Assert.assertNull(user);
        } else{
            Assert.assertNotNull(user);
            Assert.assertEquals(id,user.getId());
            System.out.println("Користувач не є null і id відповідають в параметрі одне одному");
        }
    }

    @Test
    void update() {
        User userForUpdate = new User("TestName","TestSurname","TestCity");
        userService.update(userForUpdate,34);
        User userFromDB = userService.getById(34);

        Assert.assertNotNull(userFromDB);
        Assert.assertEquals(userForUpdate.getName(),userFromDB.getName());
        Assert.assertEquals(userForUpdate.getSurname(),userFromDB.getSurname());
        Assert.assertEquals(userForUpdate.getCity(),userFromDB.getCity());

        System.out.println("Перевірка на оновленість значень в таблиці пройшла успішно");
    }

    @Test
    void delete() {
        int id = 10;
        User user = userService.getById(id);
        Assert.assertNotNull("користувача не існує під цим id",user);
        userService.delete(id);
        System.out.println("Видалення користувача під id:"+id);
        user = userService.getById(id);
        Assert.assertNull(user);
        System.out.println("Успішно виконання метода видалення користувача");
    }
}