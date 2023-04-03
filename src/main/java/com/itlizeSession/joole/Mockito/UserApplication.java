package com.itlizeSession.joole.Mockito;

import com.itlizeSession.joole.Entity.Project;
import com.itlizeSession.joole.Entity.User;
import com.itlizeSession.joole.Service.UserService;

import java.util.List;

/**
 * @ClassName UserApplication
 * @Description TODO
 * @Author Eason Lam
 * @Date 7/20/22 12:33
 * @Version 1.0
 **/
public class UserApplication {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public boolean createUser(String username, String password) {
        return userService.createUser(username, password);
    }

    public boolean delete(String password, Integer id){
        return userService.delete(password, id);
    }

    public User findUserById(Integer id) {
        return userService.findUserById(id);
    }

    //return true if user password and username exists, else false
    public boolean userLogin(String username, String password){
        return userService.userLogin(username, password);
    }

    public boolean updateUser(User user){
        return userService.updateUser(user);
    }

    //Return System.out.println saying the user was logged out successfully
    public boolean userLogout(){
        return userService.userLogout();
    }

    public boolean addProject(Project project, User user){
        return userService.addProject(project, user);
    }

    public User saveUser(User user) {
        return userService.saveUser(user);
    }

    public User findByUsername(String username){
        return userService.findByUsername(username);
    }

    public List<User> findAllUsers(){
        return userService.findAllUsers();
    }

    public boolean savePassword(Integer id, String oldPassword, String newPassword){
        return userService.savePassword(id, oldPassword, newPassword);
    }



}
