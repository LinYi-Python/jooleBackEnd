package com.itlizeSession.joole.Service.impl;

import com.itlizeSession.joole.Entity.Manufacturer;
import com.itlizeSession.joole.Repository.ManufacturerRepository;
import com.itlizeSession.joole.Service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @ClassName ManufacturerServiceImp
 * @Description TODO
 * @Author Yi Lin
 * @Date 5/16/22 03:34
 * @Version 1.0
 **/
@Service
public class ManufacturerServiceImp implements ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public Manufacturer findOneById(Integer Id){
        return manufacturerRepository.findById(Id).orElse(null);
    }

    @Override
    public List<Manufacturer> findAll(){
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer){
        return manufacturerRepository.save(manufacturer);
    }
}
