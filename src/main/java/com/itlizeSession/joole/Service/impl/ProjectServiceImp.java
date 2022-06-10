package com.itlizeSession.joole.Service.impl;

import com.itlizeSession.joole.Entity.ProjectProduct;
import com.itlizeSession.joole.Entity.Product;
import com.itlizeSession.joole.Entity.Project;
import com.itlizeSession.joole.Entity.User;
import com.itlizeSession.joole.Repository.ProductRepository;
import com.itlizeSession.joole.Repository.ProjectProductRepository;
import com.itlizeSession.joole.Repository.ProjectRepository;
import com.itlizeSession.joole.Repository.UserRepository;
import com.itlizeSession.joole.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class ProjectServiceImp implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectProductRepository projectProductRepository;

    @Override
    public Project findOneById(Integer Id){
        return projectRepository.findById(Id).orElse(null);
    }

    @Override
    public Collection<Project> findAll(){
        return projectRepository.findAll();
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public boolean create(Project project, User user) {
        if (project == null || user == null) {
            System.out.println("Null input");
            return false;
        }
//        Project target = findOneById(project.getId());
//        if (target != null) {
//            System.out.println("Project already exists");
//        }

        try {
            project.setUser(user);
            userRepository.save(user);
            projectRepository.save(project);

        } catch (Exception e){
            System.out.println("something wrong happens when creating" + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Project project) {
        if (project == null) {
            System.out.println("input is null");
            return false;
        }
        try{
            projectRepository.delete(project);
        }catch (Exception e){
            System.out.println("something wrong happens when creating" + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Project project) {
        if (project == null) {
            System.out.println("input is null");
            return false;
        }

        try {
            projectRepository.save(project);
        } catch (Exception e) {
            System.out.println("something wrong happens when updating" + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAll(){
        try{
            projectRepository.deleteAll();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;


    }

    @Override
    public Project get(Integer id) {
        if (id == null) {
            return null;
        }
        Optional<Project> result = projectRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }else{
            return null;
        }
    }

    @Override
    public List<Product> findProductsFromProject(Integer id){
        Project project = projectRepository.findById(id).orElse(null);
        List<ProjectProduct> projectProducts = projectProductRepository.findProjectProductByProject(project);
        List<Product> result = new ArrayList<>();
        for(ProjectProduct projectProduct: projectProducts) {
            result.add(projectProduct.getProduct());
        }
        return result;
    }


    @Override
    public List<Project> findProjectsFromUser(Integer id){
        User user = userRepository.findUserById(id);

        List<Project> result = projectRepository.findByUser(user);

        return result;
    }


    @Override
    public boolean addProductFromProject(Integer productId, Integer projectId){
        Product product = productRepository.findById(productId).orElse(null);
        Project project = projectRepository.findById(projectId).orElse(null);
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        ProjectProduct projectProduct = new ProjectProduct(createTime, product, project);
        try{


            ProjectProduct target = projectProductRepository.save(projectProduct);
            product.addProjectProducts(target);
            productRepository.save(product);
            project.addProjectProducts(target);
            projectRepository.save(project);
//            product.addProjectProducts(target);
//            productRepository.save(product);
//            project.addProjectProducts(target);
//            projectRepository.save(project);

        }catch (Exception e) {
            System.out.println("something wrong happens when add" + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;


    }

    @Override
    @Transactional
    public boolean deleteProductFromProject(Integer productId, Integer projectId){
        Product product = productRepository.findById(productId).orElse(null);
        Project project = projectRepository.findById(projectId).orElse(null);

        ProjectProduct projectProduct = projectProductRepository.findByProjectAndProduct(project, product);
//        ProjectProduct projectProduct = projectProductRepository.findByProduct(product);
        Integer projectProductId = projectProduct.getId();
        try{
            projectProductRepository.deleteById(projectProductId);
//            ProjectProduct projectProduct = projectProductRepository.deleteByProjectAndProduct(project, product);
//            projectProductRepository.deleteById(projectProductId);
            product.removeProjectProducts(projectProduct);
            project.removeProjectProducts(projectProduct);
            productRepository.save(product);
            projectRepository.save(project);

        }catch (Exception e) {
            System.out.println("something wrong happens when delete" + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;


    }


    @Override
    public boolean addProjectFromUser(Integer projectId, Integer userId){
        Project project = projectRepository.findById(projectId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        try {
            user.addProjects(project);
            userRepository.save(user);
            projectRepository.save(project);
        }catch (Exception e) {
            System.out.println("something wrong happens when add" + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteProjectFromUser(Integer projectId, Integer userId){
        Project project = projectRepository.findById(projectId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        try {
            user.removeProjects(project);
            userRepository.save(user);
            projectRepository.deleteById(projectId);
        }catch (Exception e) {
            System.out.println("something wrong happens when delete" + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
