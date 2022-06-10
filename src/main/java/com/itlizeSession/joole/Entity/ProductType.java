package com.itlizeSession.joole.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;
import com.itlizeSession.joole.Entity.Product;
import com.itlizeSession.joole.Entity.TechnicalDetail;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Product_type
 * @Description TODO
 * @Author Yi Lin
 * @Date 5/11/22 00:36
 * @Version 1.0
 **/
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "product_type")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;


    @Column(name = "product_type_detail")
    private String productTypeDetail;


    @CreatedDate
    @Column(name = "create_time")
    private Timestamp createTime;

    @JsonIgnore
    @OneToMany(targetEntity = Product.class, cascade = CascadeType.REMOVE, mappedBy = "productType")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Product> products = new ArrayList<>();

    @JsonIgnore
    @OneToMany(targetEntity = TechnicalDetail.class, cascade = CascadeType.REMOVE, mappedBy = "productType")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<TechnicalDetail> technicalDetails = new ArrayList<>();

    public List<Product> getProducts(){
        return products;
    }

    public void addProducts(Product product) {
        if(products.contains(product)){
            return;
        }
        products.add(product);
        product.setProductType(this);
    }

    public void removeProducts(Product product){
        if(!products.contains(product)){
            return;
        }
        products.remove(product);
        product.setProductType(null);
    }

    // next
        public List<TechnicalDetail> getTechnicalDetails(){
        return technicalDetails;
    }

    public void addTechnicalDetails(TechnicalDetail technicalDetail) {
        if(technicalDetails.contains(technicalDetail)){
            return;
        }
        technicalDetails.add(technicalDetail);
        technicalDetail.setProductType(this);
    }

    public void removeTechnicalDetails(TechnicalDetail technicalDetail){
        if(!technicalDetails.contains(technicalDetail)){
            return;
        }
        technicalDetails.remove(technicalDetail);
        technicalDetail.setProductType(null);
    }

    @Override
    public String toString(){
        return "ProductType{" + "id=" + id + "}";
    }

    public String toJson(List<String> entries){
        String result = null;
        List<String> colsContent = new ArrayList<>();
        for(String entry: entries) {
            colsContent.add(entry);
        }
        result = "{" + String.join("," , colsContent) + "}";
        return String.format("{\"ProductTypeId\" : \"%d\", \"content\" : \"%s\"}" , getId(), result);

    }


    public ProductType() {
    }

    public ProductType(String productTypeDetail, Timestamp createTime) {
        this.productTypeDetail = productTypeDetail;
        this.createTime = createTime;
    }

    public ProductType(String productTypeDetail) {
        this.productTypeDetail = productTypeDetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getProductTypeDetail() {
        return productTypeDetail;
    }

    public void setProductTypeDetail(String productTypeDetail) {
        this.productTypeDetail = productTypeDetail;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
