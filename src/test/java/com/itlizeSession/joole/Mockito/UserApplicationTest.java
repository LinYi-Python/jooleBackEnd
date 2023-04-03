package com.itlizeSession.joole.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import com.itlizeSession.joole.Entity.User;
import com.itlizeSession.joole.Service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName UserApplicationTest
 * @Description TODO
 * @Author
 * @Date 7/20/22 12:56
 * @Version 1.0
 **/
@RunWith(MockitoJUnitRunner.class)
class UserApplicationTest {

    @InjectMocks
    UserApplication userApplication = new UserApplication();

    @Mock
    @Autowired
    UserService userService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createUser() {
        when(userService.createUser("username", "password")).thenReturn(true);
        //stubbing

        Assert.assertEquals(userApplication.createUser("username", "password"), true);
    }


    @Test
    void findUserById() {
        User user = new User();
        when(userService.findUserById(5)).thenReturn(user);

        Assert.assertNotNull(userApplication.findUserById(5));
        verify(userService).findUserById(5);
    }

    @Test
    void userLogin() {
        when(userService.userLogin("username", "password")).thenReturn(true);

        Assert.assertEquals(userApplication.userLogin("username", "password"), true);
    }

    @Test
    void updateUser() {
        User user = new User("String userName", "String password", "String name",
                "String profilePictureUrl", new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()));
        when(userService.updateUser(user)).thenReturn(true);

        Assert.assertEquals(userApplication.updateUser(user), true);
    }

    @Test
    void userLogout() {
        when(userService.userLogout()).thenReturn(true);

        Assert.assertEquals(userApplication.userLogout(), true);
    }
//    public boolean savePassword(Integer id, String oldPassword, String newPassword){
//        return userService.savePassword(id, oldPassword, newPassword);
//    }
    @Test
    void savePassword() {
        when(userService.savePassword(114, "username", "username1")).thenReturn(true);
        Assert.assertEquals(userApplication.savePassword(114, "username", "username1"), true);
    }

//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username).orElse(null);
//    }

    @Test
    void findByUsername() {
        User user = new User();
        when(userService.findByUsername("test1")).thenReturn(user);
        Assert.assertNotNull(userApplication.findByUsername("test1"));
    }

    private static final String username = "username1";
    private static final String password = "password1";
    private static final String newPassword = "password";


    //integration testing
//    @Test
//    public void CreateUserAndLoginAndFindByUsernameAndSavePasswordAndLogOut(){
////        User user = new User(username, password);
//        when(userService.createUser(username, password)).thenReturn(true);
//        when(userService.userLogin(username, password)).thenReturn(true);
//        User user = userService.findByUsername(username);
//        when(userService.findByUsername(username)).thenReturn(user);
//        Integer id = user.getId();
//        when(userService.savePassword(id, password, newPassword)).thenReturn(true);
//        when(userService.userLogout()).thenReturn(true);
//
//
//    }


}