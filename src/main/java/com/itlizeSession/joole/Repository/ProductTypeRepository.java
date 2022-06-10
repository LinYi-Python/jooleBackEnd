package com.itlizeSession.joole.Repository;

import com.itlizeSession.joole.Entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName ProductTypeRepository
 * @Description TODO
 * @Author Yi Lin
 * @Date 5/16/22 02:09
 * @Version 1.0
 **/
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
    public ProductType getProductTypeByProductTypeDetail(String name);
}
