package com.itlizeSession.joole.Service.impl;

import java.sql.Timestamp;

import com.itlizeSession.joole.Entity.User;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName UserServiceImpTest
 * @Description TODO
 * @Author
 * @Date 5/17/22 10:40
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImpTest {

    @Autowired
    private UserServiceImp userService;

    @Test
    void createUser() {

        boolean actual = userService.createUser("username", "password");
        Assert.assertTrue(actual);
    }

    @Test
    void saveUser(){
        User user = new User("test1", "test1");
        System.out.println(user.getId());
        User result = userService.saveUser(user);
        System.out.println(result.getId());
        Assert.assertNotNull(result);
    }

    @Test
    void delete() {

        boolean expected = true;

        boolean actual = userService.delete("password", 5);

        Assert.assertEquals(expected, actual);

    }

    // pass
    @Test
    void findUserById() {

        User res = userService.findUserById(4);
        Assert.assertTrue(res != null);
    }

    @Test
    void userLogin() {

        boolean expected = true;

        boolean actual = userService.userLogin("username", "password");

        Assert.assertEquals(expected, actual);

    }

    @Test
    void updateUser() {

        boolean expected = true;

        User user = new User("String userName", "String password", "String name",
                "String profilePictureUrl", new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()));

        boolean actual = userService.updateUser(user);

        Assert.assertEquals(expected, actual);

    }

    @Test
    void userLogout() {

        boolean expected = true;

        boolean actual = userService.userLogout();

        Assert.assertEquals(expected, actual);

    }
}