package com.itlizeSession.joole.Service;

import com.itlizeSession.joole.Entity.Project;
import com.itlizeSession.joole.Entity.Product;
import com.itlizeSession.joole.Entity.User;

import java.util.Collection;
import java.util.List;

public interface ProjectService {
    public Project findOneById(Integer Id);

    public Collection<Project> findAll();

    public Project save(Project project);

    public boolean create(Project project, User user);

    public boolean delete(Project project);

    public boolean update(Project project);

    public boolean deleteAll();
    public Project get(Integer id);

    public List<Product> findProductsFromProject(Integer id);

    public List<Project> findProjectsFromUser(Integer id);

//    public List<Project> findProjectsFromProduct(Integer id);

    public boolean addProductFromProject(Integer productId, Integer projectId);

    public boolean deleteProductFromProject(Integer productId, Integer projectId);

    public boolean addProjectFromUser(Integer projectId, Integer userId);

    public boolean deleteProjectFromUser(Integer projectId, Integer userId);

//    public boolean addProjectFromProduct(Integer id);
//
//    public boolean deleteProjectFromProduct(Integer id);
}
