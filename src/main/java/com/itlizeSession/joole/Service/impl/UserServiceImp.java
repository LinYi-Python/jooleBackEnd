package com.itlizeSession.joole.Service.impl;

import com.itlizeSession.joole.Entity.*;
import com.itlizeSession.joole.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.itlizeSession.joole.Service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired 
    private ProjectRepository projectRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    @Override
    public boolean savePassword(Integer id, String oldPassword, String newPassword){
        User user = userRepository.findById(id).orElse(null);
        boolean isMatches = passwordEncoder.matches(oldPassword,user.getPassword());
        if(!isMatches){
            System.out.println("wrong password");
            return false;
        }
//        String temp = passwordEncoder.encode(oldPassword);
//        if(passwordEncoder.encode(oldPassword) != user.getPassword()) {
//            System.out.println("wrong password");
//            return false;
//        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        try{
            userRepository.save(user);
        }catch (Exception e) {
            System.out.println("" + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    public boolean createUser(String username, String password) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public boolean delete(String password, Integer id) {

        User target = findUserById(id);

        if (target == null || !target.getPassword().equals(password)) {
            return false;
        } else {

            try {
                userRepository.deleteById(id);
            } catch (Exception e) {
                System.out.println("" + e.getMessage());
                e.printStackTrace();
                return false;
            }

            return true;
        }


    }

    public User findUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    //return true if user password and username exists, else false
    public boolean userLogin(String username, String password) {

        User target = userRepository.findUserByUsername(username);

        if (target == null || !target.getPassword().equals(password) || !target.getUsername().equals(username)) {
            return false;
        } else {

            try {
                System.out.println("\nUser Login: " + target.getUsername() + " " + target.getPassword() + " was successfull");
            } catch (Exception e) {
                System.out.println("" + e.getMessage());
                e.printStackTrace();
                return false;
            }

            return true;
        }


    }

    public boolean updateUser(User user) {

        try {
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;


    }

    //Return System.out.println saying the user was logged out successfully
    public boolean userLogout() {


        try {
            System.out.println("\nUser Logged out");
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean addProject(Project project, User user) {
        try {
            project.setUser(user);
            userRepository.save(user);
            projectRepository.save(project);
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
