package com.itlizeSession.joole.Service;

import com.itlizeSession.joole.Entity.Manufacturer;
import com.itlizeSession.joole.Entity.Sale;

import java.util.List;

/**
 * @ClassName SaleService
 * @Description TODO
 * @Author Yi Lin
 * @Date 5/16/22 07:09
 * @Version 1.0
 **/
public interface SaleService {
    public Sale findOneById(Integer Id);

    public List<Sale> findAll();

    public Sale save(Sale sale);
}
