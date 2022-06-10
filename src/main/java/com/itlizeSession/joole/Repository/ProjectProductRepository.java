package com.itlizeSession.joole.Repository;

import com.itlizeSession.joole.Entity.Project;
import com.itlizeSession.joole.Entity.Product;
import com.itlizeSession.joole.Entity.ProjectProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



public interface ProjectProductRepository extends JpaRepository<ProjectProduct, Integer> {
    List<ProjectProduct> findProjectProductByProject(Project project);

    ProjectProduct findByProduct(Product product);


    ProjectProduct findByProjectAndProduct(Project project, Product product);

    ProjectProduct deleteByProjectAndProduct(Project project, Product product);

    // user - project - products
    // find one users its projects add // delete //

    // find one projects its products add// delete//
}
