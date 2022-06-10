package com.itlizeSession.joole.Service.impl;

import com.itlizeSession.joole.Entity.Product;
import com.itlizeSession.joole.Entity.ProductType;
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
class ProductTypeServiceImpTest {


    @Autowired
    private ProductTypeServiceImp productTypeService;

    private ProductType productType = new ProductType();

    @Test
    void findOneById() {

        int id = 1;
//        productType.setId(id);
//        ProductType expected = productType;

        ProductType actual = productTypeService.findOneById(id);
        Assert.assertTrue(actual != null);
//        Assert.assertEquals(expected, actual);
    }

    @Test
    void findAll() {

//        List<ProductType> expected = new ArrayList<>();
        //expected.add(productType);

        List<ProductType> actual = productTypeService.findAll();
        Assert.assertTrue(actual != null);
//        Assert.assertEquals(expected, actual);
    }

    @Test
    void save() {
//        ProductType expected = productType;

        ProductType actual = productTypeService.save(productType);
        Assert.assertTrue(actual != null);
//        Assert.assertEquals(expected, actual);
    }

    @Test
    void findByName() {

        String description = "Fans";
//        productType.setProductTypeDetail(description);
//        productTypeService.save(productType);

//        ProductType expected = productType;

        ProductType actual = productTypeService.findByName(description);
        Assert.assertTrue(actual != null);
//        Assert.assertEquals(expected, actual);
    }

    @Test
    void create() {

//        boolean expected = true;
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        ProductType productType = new ProductType("testName", createTime);
//        productType.setId(666);
        boolean actual = productTypeService.create(productType);
        Assert.assertTrue(actual);
//        Assert.assertEquals(expected, actual);
    }

    @Test
    void delete() {

//        boolean expected = true;

        ProductType deleteTest = new ProductType();
        productTypeService.save(deleteTest);
        boolean actual = productTypeService.delete(deleteTest);
        Assert.assertTrue(actual);
//        Assert.assertEquals(expected, actual);
    }

    @Test
    void get() {

        int id = 1;


        ProductType actual = productTypeService.get(id);

        Assert.assertTrue(actual != null);
    }

    @Test
    void update() {

        ProductType productType = productTypeService.get(36);

        boolean actual = productTypeService.update(productType);
        Assert.assertTrue(actual);
    }

}