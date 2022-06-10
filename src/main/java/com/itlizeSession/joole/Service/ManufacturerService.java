package com.itlizeSession.joole.Service;

import com.itlizeSession.joole.Entity.Manufacturer;

import java.util.List;

/**
 * @ClassName ManufacturerService
 * @Description TODO
 * @Author Yi Lin
 * @Date 5/16/22 03:34
 * @Version 1.0
 **/
public interface ManufacturerService {
    public Manufacturer findOneById(Integer Id);

    public List<Manufacturer> findAll();

    public Manufacturer save(Manufacturer manufacturer);
}
