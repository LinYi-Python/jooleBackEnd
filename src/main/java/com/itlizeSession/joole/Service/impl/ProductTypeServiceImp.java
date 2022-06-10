package com.itlizeSession.joole.Service.impl;

import com.itlizeSession.joole.Entity.ProductType;
import com.itlizeSession.joole.Repository.ProductTypeRepository;
import com.itlizeSession.joole.Service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @ClassName ProductTypeServiceImp
 * @Description TODO
 * @Author Yi Lin
 * @Date 5/16/22 02:51
 * @Version 1.0
 **/
@Service
public class ProductTypeServiceImp implements ProductTypeService{
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public ProductType findOneById(Integer Id){
        return productTypeRepository.findById(Id).orElse(null);
    }

    @Override
    public List<ProductType> findAll(){
        return productTypeRepository.findAll();
    }

    @Override
    public ProductType save(ProductType productType){
        return productTypeRepository.save(productType);
    }

    @Override
    public ProductType findByName(String name){
        return productTypeRepository.getProductTypeByProductTypeDetail(name);
    }

    @Override
    public boolean create(ProductType productType){
        if(productType == null) {
            System.out.println("null input");
            return false;
        }

//        ProductType target = findOneById(productType.getId());
//        if(target != null) {
//            System.out.println("ProductType already exists");
//            return false;
//        }

        try{
            productTypeRepository.save(productType);

        }catch (Exception e){
            System.out.println("something is wrong" + e.getMessage());
            e.printStackTrace();
            return false;
        }

            return true;
    }

    @Override
    public boolean delete(ProductType productType) {
        ProductType target = findOneById(productType.getId());
        if(target == null) {
            System.out.println("there is nothing for us to delete");
            return false;
        }
        try{
            productTypeRepository.delete(target);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ProductType get(Integer id) {
        ProductType target = findOneById(id);
        if(target == null) {
            System.out.println("there is nothing for us to delete");
            return null;
        }
            return target;

    }

    @Override
    public boolean update(ProductType productType) {
        ProductType target = findOneById(productType.getId());
        if(target == null) {
            System.out.println("there is nothing for us to delete");
            return false;
        }
        try{
            productTypeRepository.save(productType);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
