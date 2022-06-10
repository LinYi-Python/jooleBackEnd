package com.itlizeSession.joole.Repository;

import com.itlizeSession.joole.Entity.Project;
import com.itlizeSession.joole.Entity.ProjectProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName ProjectProductRepositoryTest
 * @Description TODO
 * @Author
 * @Date 5/25/22 15:02
 * @Version 1.0
 **/
@SpringBootTest
class ProjectProductRepositoryTest {


    @Autowired
    private ProjectProductRepository projectProductRepository;

    @Test
    private ProjectProduct save(){
        ProjectProduct projectProduct = new ProjectProduct();
//        projectProduct.setId(1);
//        System.out.println(projectProduct.toString());
        System.out.println(projectProduct.getId());
        projectProductRepository.save(projectProduct);
        return projectProduct;
    }

}