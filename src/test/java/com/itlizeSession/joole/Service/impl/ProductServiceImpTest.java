package com.itlizeSession.joole.Service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.itlizeSession.joole.Entity.*;
import com.itlizeSession.joole.Entity.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName ProductServiceImpTest
 * @Description TODO
 * @Author
 * @Date 5/16/22 15:43
 * @Version 1.0
 **/
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImpTest {

    @Autowired
    private ProductServiceImp productService;

    @Autowired
    private TechnicalDetailServiceImp technicalDetailService;

    @Autowired
    private ManufacturerServiceImp manufacturerService;

    @Autowired
    private ProductTypeServiceImp productTypeService;

    @Autowired
    private SaleServiceImp saleService;



//    private Product product = new Product();

    //pass
    @Test
    void findOneById() {

                    Product res = productService.get(1);
                    Assert.assertTrue(res!=null);



    }

    //pass
    @org.junit.jupiter.api.Test
    void findAll() {

        List<Product> res = productService.findAll();
        Assert.assertTrue(res!=null);
    }

    //pass
    @org.junit.jupiter.api.Test
    void save() {


        Product test = new Product();
        Product res = productService.save(test);
        Assert.assertTrue(res!=null);

    }

    @org.junit.jupiter.api.Test
    void create() {


        Product product = new Product("Test1", 2022, "Meta");

        Manufacturer manufacturer = new Manufacturer(
                "department1", " 666", " manu1@gmail.com",
                "manu1.com");

        ProductType productType = new ProductType("TypeTest1");

        TechnicalDetail technicalDetail = new TechnicalDetail("TestAirflow",
                6000);

        Sale sale = new Sale("saletest1", "saletest1.com", "111", " testsale@gmail.com");

        boolean result = productService.create(product, manufacturer, technicalDetail, productType, sale);
        Assert.assertTrue(result);

    }

    //pass
    @org.junit.jupiter.api.Test
    void update() {

        Product product = new Product("Test1", 2022, "Meta");
        boolean result = productService.update(product);
        Assert.assertTrue(result);

    }

    //pass
    @org.junit.jupiter.api.Test
    void delete() {
//
//

        Product product = productService.findOneById(4);

        boolean actual = productService.delete(product);

        Assert.assertTrue(actual);
    }

    //pass
    @org.junit.jupiter.api.Test
    void get() {

        Product res = productService.get(1);
        Assert.assertTrue(res!=null);
    }

    //pass
    @org.junit.jupiter.api.Test
    void deleteAll() {

        boolean expected = true;
        boolean actual = productService.deleteAll();
        Assert.assertEquals(expected, actual);
    }


    @org.junit.jupiter.api.Test
    void findProducesByProductType() {

        String name = "Air-condition";
        List<Product> expected = new ArrayList<>();

//        Product p2 = productService.get(2);
        expected.add(productService.get(3));
//        expected.add(p2);

        List<Product> actual = productService.findProducesByProductType(name);
        Assert.assertTrue(actual.equals(expected));

    }

//    @org.junit.jupiter.api.Test
//    void findProductsByProductTypeAndTechnicalDetail() {
//        List<Product> expected = new ArrayList<>();
//        Product p1 = productService.get(1);
//        expected.add(p1);
//
//        String name = "Fans";
//        TechnicalDetail technicalDetail = technicalDetailService.findOneById(1);
//        List<Product> actual = productService.findProductsByProductTypeAndTechnicalDetail(name, technicalDetail);
//        Assert.assertTrue(actual != null);
////        Assert.assertEquals(expected, actual);
//        System.out.println("true");
//    }
//
//    @org.junit.jupiter.api.Test
//    void findProductsByProductTypeAndTechnicalDetailAndModelYearAndBrand() {
//        List<Product> expected = new ArrayList<>();
//        Product p1 = productService.get(1);
//        expected.add(p1);
//
//        String name = "Fans";
//        TechnicalDetail technicalDetail = technicalDetailService.findOneById(1);
//        List<Product> actual = productService.findProductsByProductTypeAndTechnicalDetailAndModelYearAndBrand(
//                name, technicalDetail, 2000, "Apple");
//        Assert.assertTrue(actual != null);
////        Assert.assertEquals(expected, actual);
////        System.out.println("true");
//    }
//
//    @org.junit.jupiter.api.Test
//    void findProductsTechnicalDetailBypProductId(){
//
//        TechnicalDetail expected = technicalDetailService.findOneById(1);
//
//
//        TechnicalDetail actual = productService.findProductsTechnicalDetailBypProductId(1);
//        Assert.assertTrue(actual != null);
////        Assert.assertEquals(expected, actual);
//    }

    @org.junit.jupiter.api.Test
    void findManufacturerByProductId(){
        Manufacturer expected = manufacturerService.findOneById(1);

        Manufacturer actual = productService.findManufacturerByProductId(1);
        Assert.assertTrue(actual != null);
//        Assert.assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void findSaleByProductId(){
        Sale expected = saleService.findOneById(1);

        Sale actual = productService.findSaleByProductId(1);
        Assert.assertTrue(actual != null);
//        Assert.assertEquals(expected, actual);
    }

}