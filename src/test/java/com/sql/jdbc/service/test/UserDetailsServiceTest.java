package com.sql.jdbc.service.test;

import com.sql.jdbc.entity.UserDetail;
import com.sql.jdbc.service.UserDetailsService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


public class UserDetailsServiceTest {
    private UserDetailsService userDetailsService;
    @BeforeEach
    public void createService(){
        userDetailsService = new UserDetailsService();
    }
    @Test
    public void add() {
        UserDetail detail = new UserDetail(50,37,"0982233440","Doctor");
        userDetailsService.add(detail);

        UserDetail detailFromDB = userDetailsService.getById(detail.getId());
        Assert.assertNotNull(detailFromDB);
        Assert.assertEquals(detail.getId(),detailFromDB.getId());
        Assert.assertEquals(detail.getAge(),detailFromDB.getAge());
        Assert.assertEquals(detail.getPhone(),detailFromDB.getPhone());
        Assert.assertEquals(detail.getJob(),detailFromDB.getJob());

        userDetailsService.delete(detailFromDB.getId());
    }

    @Test
    public void getAll() {
        List<UserDetail> details = userDetailsService.getAll();

        Assert.assertNotNull(details);
        Assert.assertNotEquals(0,details.size());
    }

    @Test
    public void getById() {
        UserDetail detail = userDetailsService.getById(37);
        Assert.assertNotNull(detail);
        Assert.assertEquals(detail.getId(),37);

    }

    @Test
    public void update() {
        UserDetail userDetail = new UserDetail(20,"0982370111","cleaner");

        userDetailsService.update(userDetail,37);
        UserDetail detailFromDB = userDetailsService.getById(37);

        Assert.assertNotNull(detailFromDB);
        Assert.assertEquals(detailFromDB.getAge(),userDetail.getAge());
        Assert.assertEquals(detailFromDB.getPhone(),userDetail.getPhone());
        Assert.assertEquals(detailFromDB.getJob(),userDetail.getJob());
    }

    @Test
    public void delete() {
        int id = 39;
        UserDetail userDetail = userDetailsService.getById(id);

        Assert.assertNotNull(userDetail);
        userDetailsService.delete(id);
        userDetail = userDetailsService.getById(id);
        Assert.assertNull(userDetail);
    }
}