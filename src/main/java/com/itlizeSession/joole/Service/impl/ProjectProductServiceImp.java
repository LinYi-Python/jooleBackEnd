package com.itlizeSession.joole.Service.impl;

import com.itlizeSession.joole.Entity.Product;
import com.itlizeSession.joole.Entity.Project;
import com.itlizeSession.joole.Entity.ProjectProduct;
import com.itlizeSession.joole.Repository.ProductRepository;
import com.itlizeSession.joole.Repository.ProjectProductRepository;
import com.itlizeSession.joole.Repository.ProjectRepository;
import com.itlizeSession.joole.Service.ProjectProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectProductServiceImp implements ProjectProductService {

    @Autowired
    ProjectProductRepository projectProductRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Collection<ProjectProduct> findALl() {
        return projectProductRepository.findAll();
    }

    @Override
    public ProjectProduct findById(Integer Id) {
        return projectProductRepository.findById(Id).orElse(null);
    }

    @Override
    public boolean create(ProjectProduct projectProduct, Project project, Product product) {
        // Optional<ProjectProduct> target = projectProductRepository.findById(projectProduct.getId());
        if (projectProduct == null || project ==null || project ==null) {
            return false;
        }
        if(getOne(product,project) !=null){
            System.out.println("the project has already added to the project!");
            return false;
        }


        try {
            projectProductRepository.save(projectProduct);
            project.addProjectProducts(projectProduct);
            product.addProjectProducts(projectProduct);
            projectRepository.save(project);
            productRepository.save(product);


        } catch (Exception e){
            System.out.println("something wrong happens when creating" + projectProduct.toString()+
                    "the exception as showing here " +e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(ProjectProduct projectProduct) {
        if (projectProduct == null) {
            System.out.println("input is null");
            return false;
        }
        System.out.println("try to delete product" + projectProduct.getId());

        try {
            projectProductRepository.delete(projectProduct);
        } catch (Exception e) {
            System.out.println("something wrong happens when deleting" + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(ProjectProduct projectProduct) {
        if (projectProduct == null) {
            return false;
        }
        try {
            projectProductRepository.save(projectProduct);
        } catch (Exception e) {
            System.out.println("something wrong happens when updating" + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ProjectProduct get(Integer id) {
        if (id == null) {
            return null;
        }
        Optional<ProjectProduct> result = projectProductRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }else{
            return null;
        }

    }
    public ProjectProduct getOne(Product product, Project project){
       ProjectProduct projectProduct =
               projectProductRepository.findByProjectAndProduct(project, product);
       return projectProduct;
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
    public boolean addProductFromProject(Integer productId, Integer projectId){
        Product product = productRepository.findById(productId).orElse(null);
        Project project = projectRepository.findById(projectId).orElse(null);
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        //check duplicate issue
        ProjectProduct projectProduct = new ProjectProduct(createTime, product, project);
        try{

//            projectProduct.getId(); //
            ProjectProduct target = projectProductRepository.save(projectProduct);
            product.addProjectProducts(target);
            productRepository.save(product);
            project.addProjectProducts(target);
            projectRepository.save(project);

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
        Integer projectProductId = projectProduct.getId();
        try{
            projectProductRepository.deleteById(projectProductId);

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

}
