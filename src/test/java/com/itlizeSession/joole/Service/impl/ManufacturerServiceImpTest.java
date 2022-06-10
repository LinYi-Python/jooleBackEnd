package com.itlizeSession.joole.Service.impl;

import com.itlizeSession.joole.Entity.Manufacturer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @ClassName ManufacturerServiceImpTest
 * @Description TODO
 * @Author
 * @Date 5/17/22 10:39
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class ManufacturerServiceImpTest {

    @Autowired
    private ManufacturerServiceImp manufactureService;

    private Manufacturer manufacturer = new Manufacturer();

    @Test
    void findOneById() {

        int id = 1;
        manufacturer.setId(id);

//        Manufacturer expected = manufacturer;

        Manufacturer actual = manufactureService.findOneById(id);
        Assert.assertTrue(actual != null);

//        Assert.assertEquals(expected, actual);

    }

    @Test
    void findAll() {

//        List<Manufacturer> expected = new ArrayList<Manufacturer>();

        List<Manufacturer> actual = manufactureService.findAll();

//        Assert.assertEquals(expected, actual);
        Assert.assertTrue(actual != null);

    }

    @Test
    void save() {
//        Manufacturer expected = manufacturer;

        Manufacturer actual = manufactureService.save(manufacturer);

//        Assert.assertEquals(expected, actual);
        Assert.assertTrue(actual != null);
    }
}