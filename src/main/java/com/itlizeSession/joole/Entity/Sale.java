package com.itlizeSession.joole.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Sale
 * @Description TODO
 * @Author Yi Lin
 * @Date 5/11/22 01:28
 * @Version 1.0
 **/

@EntityListeners(AuditingEntityListener.class)
@Entity(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "web_url")
    private String webUrl;

//    @Column(name = "product_id", length = 20)
//    private Integer productId;

    @CreatedDate
    @Column(name = "create_time")
    private Timestamp createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Timestamp updateTime;

    @JsonIgnore
    @OneToOne(targetEntity = Product.class, cascade = CascadeType.REMOVE, mappedBy = "sale")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Product product = new Product();

//    public List<Product> getProducts(){
//        return products;
//    }
//
//    public void addProducts(Product product) {
//        if(products.contains(product)){
//            return;
//        }
//        products.add(product);
//        product.setSale(this);
//    }
//
//    public void removeProducts(Product product){
//        if(!products.contains(product)){
//            return;
//        }
//        products.remove(product);
//        product.setSale(null);
//    }

    @Override
    public String toString(){
        return "Sale{" + "id=" + id + "}";
    }

    public String toJson(List<String> entries){
        String result = null;
        List<String> colsContent = new ArrayList<>();
        for(String entry: entries) {
            colsContent.add(entry);
        }
        result = "{" + String.join("," , colsContent) + "}";
        return String.format("{\"SaleId\" : \"%d\", \"content\" : \"%s\"}" , getId(), result);

    }

    public Sale() {
    }

    public Sale(String name, String email, String phone, String webUrl) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.webUrl = webUrl;
    }

    public Sale(String name, String email, String phone,
                String webUrl,
                Timestamp createTime, Timestamp updateTime) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.webUrl = webUrl;
//        this.productId = productId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product products) {
        this.product = products;
    }

    //    public Integer getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Integer productId) {
//        this.productId = productId;
//    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
