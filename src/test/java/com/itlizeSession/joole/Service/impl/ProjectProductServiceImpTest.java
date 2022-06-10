package com.itlizeSession.joole.Service.impl;

import com.itlizeSession.joole.Entity.*;
import com.itlizeSession.joole.Entity.ProjectProduct;
import com.itlizeSession.joole.Repository.ProductRepository;
import com.itlizeSession.joole.Service.ProductService;
import com.itlizeSession.joole.Service.ProjectProductService;
import com.itlizeSession.joole.Service.ProjectService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName ProjectProductServiceImpTest
 * @Description TODO
 * @Author
 * @Date 5/25/22 14:45
 * @Version 1.0
 **/

@SpringBootTest
class ProjectProductServiceImpTest {



    @Autowired
    private ProductService productService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectProductService projectProductService;

    @Test
    void create() {
        ProjectProduct projectProduct = new ProjectProduct();
        System.out.println(projectProduct);

        System.out.println(projectProduct.getId());
        Product product = productService.get(1);
        Project project = projectService.get(1);
        projectProduct.setProject(project);
        projectProduct.setProduct(product);


        Assert.assertNotNull(projectProduct);
    }

    @Test
    void findProductsFromProject() {
        Integer id = 1;
        List<Product> products = projectProductService.findProductsFromProject(id);
        System.out.println(products.toString());
        Assert.assertNotNull(products);
    }

    @Test
    void addProductFromProject() {
        Integer productId = 1;
        Integer projectId = 1;
        boolean result = projectProductService.addProductFromProject(productId, projectId);
        Assert.assertTrue(result);
    }

    @Test
    void deleteProductFromProject() {
        Integer productId = 1;
        Integer projectId = 1;
        boolean result = projectProductService.deleteProductFromProject(productId, projectId);
        Assert.assertTrue(result);
    }

}