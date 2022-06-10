package com.itlizeSession.joole.Service.impl;

import com.itlizeSession.joole.Entity.Product;
import com.itlizeSession.joole.Entity.ProductType;
import com.itlizeSession.joole.Entity.Project;
import com.itlizeSession.joole.Entity.User;
import com.itlizeSession.joole.Service.ProjectService;
import com.itlizeSession.joole.Service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName ProductTypeServiceImpTest
 * @Description TODO
 * @Author
 * @Date 5/17/22 10:39
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class ProjectServiceImpTest {


    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;


    private ProductType productType = new ProductType();

    @Test
    void findOneById() {

        Project actual = projectService.get(3);
        Assert.assertTrue(actual != null);
    }

    @Test
    void findAll() {
        Collection<Project> actual = projectService.findAll();
        Assert.assertTrue(actual != null);
    }

//    @Test
//    void save() {
//        Project project = new Project();
//        Project actual = projectService.save(project);
//        Assert.assertEquals(project, actual);
//    }

    @Test
    void create() {
        User user = new User("b", "x","q", null, null,null);
        System.out.println(user.getId());
        Project toAdd = new Project("ProjectTest1", user, null, null);
        boolean isSuccessful = projectService.create(toAdd, user);
        Assert.assertTrue(isSuccessful);
    }

    @Test
    void update() {
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());


        Project project = projectService.findOneById(1);
        project.setProjectName("ProjectTestChange1");
        project.setUpdateTime(updateTime);
        boolean result = projectService.update(project);
        Assert.assertTrue(result);
    }


    @Test
    void delete() {
        Project toDelete = projectService.get(2);
        boolean isSuccessful = projectService.delete(toDelete);
        Assert.assertTrue(isSuccessful);
    }

    @Test
    void findProductsFromProject(){
        Integer id = 1;
        List<Product> result = projectService.findProductsFromProject(id);
        Assert.assertTrue(result != null);
    }

    @Test
    void findProjectsFromUser(){
        Integer id = 1;
        List<Project> result = projectService.findProjectsFromUser(id);
        Assert.assertTrue(result != null);
    }

    @Test
    void addProductFromProject() {
        Integer productId = 1;
        Integer projectId = 1;
        boolean result = projectService.addProductFromProject(productId, projectId);
        Assert.assertTrue(result);
    }

    @Test
    void deleteProductFromProject() {
        Integer productId = 1;
        Integer projectId = 1;
        boolean result = projectService.deleteProductFromProject(productId, projectId);
        Assert.assertTrue(result);
    }

    @Test
    void addProjectFromUser(){
        Integer projectId = 1;
        Integer userId = 1;
        boolean result = projectService.addProjectFromUser(projectId, userId);
        Assert.assertTrue(result);
    }

    @Test
    void deleteProjectFromUser() {
        Integer projectId = 1;
        Integer userId = 1;
        boolean result = projectService.deleteProjectFromUser(projectId, userId);
        Assert.assertTrue(result);
    }

}
