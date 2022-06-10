package com.itlizeSession.joole.Service.impl;

import com.itlizeSession.joole.Entity.TechnicalDetail;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName TechnicalDetailServiceImpTest
 * @Description TODO
 * @Author
 * @Date 5/17/22 10:40
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class TechnicalDetailServiceImpTest {

    @Autowired
    private TechnicalDetailServiceImp technicalService;


    private TechnicalDetail technicalDetail = new TechnicalDetail();


    @Test
    void findOneById() {

        int id = 1;
//        technicalDetail.setId(id);
//        TechnicalDetail expected = technicalDetail;

        TechnicalDetail actual = technicalService.findOneById(id);
        Assert.assertTrue(actual != null);
//        Assert.assertEquals(expected, actual);
    }

    @Test
    void findAll() {
        List<TechnicalDetail> actual = technicalService.findAll();

        Assert.assertTrue(actual != null);
    }

    @Test
    void save() {

//        TechnicalDetail expected = technicalDetail;

        TechnicalDetail actual = technicalService.save(technicalDetail);

        Assert.assertTrue(actual != null);
    }

    @Test
    void getTechnicalDetailByName() {

        String test = "airflow";

        List<TechnicalDetail> expected = new ArrayList<>();
        TechnicalDetail t1 = technicalService.findOneById(1);
        TechnicalDetail t2 = technicalService.findOneById(5);
        expected.add(t1);
        expected.add(t2);

        List<TechnicalDetail> actual = technicalService.getTechnicalDetailByName(test);
        Assert.assertTrue(actual != null);
//        Assert.assertEquals(expected, actual);


    }

    @Test
    void getTechnicalDetailByProductType() {
        Integer productTypeId = 1;
        List<TechnicalDetail> result = technicalService.getTechnicalDetailByProductType(productTypeId);
        System.out.println(result.toString());
        Assert.assertNotNull(result);
    }

    @Test
    void getTechnicalDetailByProduct() {
        Integer productId = 37;
        List<TechnicalDetail> result = technicalService.getTechnicalDetailByProduct(productId);
        System.out.println(result.toString());
        Assert.assertNotNull(result);
    }

}