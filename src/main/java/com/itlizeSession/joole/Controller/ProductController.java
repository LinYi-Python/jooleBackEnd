package com.itlizeSession.joole.Controller;

import com.itlizeSession.joole.Entity.*;
import com.itlizeSession.joole.Repository.UserRepository;
import com.itlizeSession.joole.Service.*;
import com.itlizeSession.joole.Service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.List;


/**
 * @ClassName ProductController
 * @Description TODO
 * @Author Yi Lin
 * @Date 5/17/22 13:34
 * @Version 1.0
 **/
@RestController
@RequestMapping("/ProductController")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private TechnicalDetailService technicalDetailService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private UserRepository userRepository;

//    public static void main() {
//
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
//            .getPrincipal();
//    String username = userDetails.getUsername();
//    User user = userRepository.findByUsername(username).orElse(null);
//    Integer userId = user.getId();
//    }
//


    /**
     * get all product type for a list
     * @return a list of all product type
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/getProductType")
    public ResponseEntity<?> getAllProductType(){
        List<ProductType> productTypesList = productTypeService.findAll();

//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
//            .getPrincipal();
//    String username = userDetails.getUsername();
//    User user = userRepository.findByUsername(username).orElse(null);
//    Integer userId = user.getId();
//    System.out.println("Here is it!");
//    System.out.println(userId);


        return new ResponseEntity<>(productTypesList, HttpStatus.OK);
    }

    /**
     * get all product list for one product type
     * @return a list of all product type
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/getAllProductFromProductType")
    public ResponseEntity<?> getAllProductFromProductType(@RequestParam("ProductType") String productTypeName){
        List<Product> productList = productService.findProducesByProductType(productTypeName);
        
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    /**
     * get a product information by productId
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/getProductInfo")
    public ResponseEntity<?> getProductById(@RequestParam("ProductId") Integer productId){
        Product product = productService.get(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     *create a product
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANUFACTURER')")
    @PostMapping("/createProduct")
    public ResponseEntity<?> createProduct(@RequestParam("Productname") String name,
                                           @RequestParam("model_year") Integer modelYear,
                                           @RequestParam("brand") String brand,
                                           @RequestParam("product_type_id") Integer productTypeId,
                                           @RequestParam("technical_detail_id") Integer technicalDetailId,
                                           @RequestParam("manufacturer_detail_id") Integer manufacturerDetailId,
                                           @RequestParam("sale_id") Integer saleId
                                           ){
//        Timestamp createTime = new Timestamp(System.currentTimeMillis());
//        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        Product product = new Product(name, modelYear, brand);
        ProductType productType = productTypeService.findOneById(productTypeId);
        TechnicalDetail technicalDetail = technicalDetailService.findOneById(technicalDetailId);
        Manufacturer manufacturer = manufacturerService.findOneById(manufacturerDetailId);
        Sale sale = saleService.findOneById(saleId);
        boolean result = productService.create(product, manufacturer, technicalDetail, productType, sale);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     *update a product information
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANUFACTURER')")
    @PostMapping("/updateProduct")
    public ResponseEntity<?> updateProduct(@RequestParam("ProductId") Integer productId,
                                           @RequestParam("model_year") Integer modelYear,
                                           @RequestParam("brand") String brand){
        Product product = productService.get(productId);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(modelYear != null){
            product.setModelYear(modelYear);
        }
        if(brand != null) {
            product.setBrand(brand);
        }
        product.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        boolean result = productService.update(product);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     *
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANUFACTURER')")
    @PostMapping("/deleteProductByProductId")
    public ResponseEntity<?> deleteProductById(@RequestParam("ProductId") Integer productId){
        Product product = productService.get(productId);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean result = productService.delete(product);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
//
//
    /**
     * @Return return TechnicalDetail information
     */
//    @GetMapping("/getProductTechnicalDetailById")
//    public ResponseEntity<?> getProductTechnicalDetailById(@RequestParam("ProductId") Integer productId){
//        TechnicalDetail result = productService.findProductsTechnicalDetailBypProductId(productId);
//        if(result == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }else {
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        }
//    }
//
    /**
     * @Return return one product its manufacturerInformation information
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANUFACTURER', 'ROLE_USER')")
    @GetMapping("/getManufacturerByProductId")
    public ResponseEntity<?>  getProductManufacturerById(@RequestParam("ProductId") Integer productId){
        Manufacturer result = productService.findManufacturerByProductId(productId);
        if(result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    /**
     * @Return return one product its Sale information
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANUFACTURER', 'ROLE_USER')")
    @GetMapping("/getSaleByProductId")
    public ResponseEntity<?> getProductSaleById(@RequestParam("ProductId") Integer productId){
        Sale result = productService.findSaleByProductId(productId);
        if(result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }


    /**
     * @Return getAllProductFromProductTypeAndTechnicalDetailAndModelYearAndBrand
     */
//    @GetMapping("getAllProductFromProductTypeAndTechnicalDetailAndModelYearAndBrand")
//    public ResponseEntity<?> getAllProductFromProductTypeAndTechnicalDetailAndModelYearAndBrand(@RequestParam("name") String name,
//                                                                                                @RequestParam("techicalDetailId") Integer technicalDetailId,
//                                                                                                @RequestParam("modelYear") Integer modelYear,
//                                                                                                @RequestParam("brand") String brand){
//        TechnicalDetail technicalDetail = technicalDetailService.findOneById(technicalDetailId);
//        List<Product> result = productService.findProductsByProductTypeAndTechnicalDetailAndModelYearAndBrand(name, technicalDetail, modelYear, brand);
//        if(result == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }else {
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        }
//    }



}
