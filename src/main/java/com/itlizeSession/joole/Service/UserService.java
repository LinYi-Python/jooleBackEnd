package com.itlizeSession.joole.Service;

import com.itlizeSession.joole.Entity.Project;
import com.itlizeSession.joole.Entity.User;

import java.util.List;

public interface UserService {

    public boolean createUser(String username, String password);

    public boolean delete(String password, Integer id);

    public User findUserById(Integer id);

    //return true if user password and username exists, else false
    public boolean userLogin(String username, String password);

    public boolean updateUser(User user);

    //Return System.out.println saying the user was logged out successfully
    public boolean userLogout();

    public boolean addProject(Project project, User user);

    public User saveUser(User user);

    public User findByUsername(String username);

    public List<User> findAllUsers();

    public boolean savePassword(Integer id, String oldPassword, String newPassword);

}
