package com.itlizeSession.joole.Service.impl;

import com.itlizeSession.joole.Entity.Sale;

import com.itlizeSession.joole.Repository.SaleRepository;
import com.itlizeSession.joole.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.List;

/**
 * @ClassName SaleServiceImp
 * @Description TODO
 * @Author Yi Lin
 * @Date 5/16/22 07:09
 * @Version 1.0
 **/
@Service
public class SaleServiceImp implements SaleService{
    @Autowired
    private SaleRepository saleRepository;

    @Override
    public Sale findOneById(Integer Id){
        return saleRepository.findById(Id).orElse(null);
    }

    @Override
    public List<Sale> findAll(){
        return saleRepository.findAll();
    }

    @Override
    public Sale save(Sale sale){
        return saleRepository.save(sale);
    }
}
