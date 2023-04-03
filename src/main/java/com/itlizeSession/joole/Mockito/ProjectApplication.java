package com.itlizeSession.joole.Mockito;

import com.itlizeSession.joole.Entity.Product;
import com.itlizeSession.joole.Entity.Project;
import com.itlizeSession.joole.Entity.User;
import com.itlizeSession.joole.Service.ProjectService;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName ProjectApplication
 * @Description TODO
 * @Author Eason Lin
 * @Date 7/20/22 15:11
 * @Version 1.0
 **/
public class ProjectApplication {
    private ProjectService projectService;

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public Project findOneById(Integer id) {
        return projectService.findOneById(id);
    }

    public Collection<Project> findAll() {
        return projectService.findAll();
    }

//    public Project save(Project project);
//
//    public boolean create(Project project, User user);
//
//    public boolean delete(Project project);
//
//    public boolean update(Project project);
//
//    public boolean deleteAll();
//    public Project get(Integer id);

    public List<Product> findProductsFromProject(Integer id) {
        return projectService.findProductsFromProject(id);
    }

    public List<Project> findProjectsFromUser(Integer id) {
        return projectService.findProjectsFromUser(id);
    }


    public boolean addProductFromProject(Integer productId, Integer projectId) {
        return projectService.addProductFromProject(productId, projectId);
    }

    public boolean deleteProductFromProject(Integer productId, Integer projectId) {
        return projectService.deleteProductFromProject(productId, projectId);
    }

    public boolean addProjectFromUser(Integer projectId, Integer userId) {
        return projectService.addProjectFromUser(projectId, userId);
    }

    public boolean deleteProjectFromUser(Integer projectId, Integer userId) {
        return projectService.deleteProjectFromUser(projectId, userId);
    }
}
