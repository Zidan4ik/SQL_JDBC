package com.sql.jdbc.service.paramatrized;

import com.sql.jdbc.entity.User;
import com.sql.jdbc.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.CsvSource;

class ParameterizedTest {
    private UserService userService;

    @BeforeEach
    public void create() {
        userService = new UserService();
    }

    @org.junit.jupiter.params.ParameterizedTest
    @CsvSource
            ({
                    "36,N1,S1,C1",
                    "35,N2,S2,C2",
                    "-1,N3,S3,C3"
            })
    public void TestParametrizedAdd(int id, String name, String surname, String city) {
        User user = new User(id, name, surname, city);

        userService.add(user);
        User userFromDB = userService.getById(user.getId());

        Assert.assertEquals(user.getId(), userFromDB.getId());
        Assert.assertEquals(user.getName(), userFromDB.getName());
        Assert.assertEquals(user.getSurname(), userFromDB.getSurname());
        Assert.assertEquals(user.getCity(), userFromDB.getCity());

        System.out.println("перевірка методу save до database пройшла успішно");
    }
}
