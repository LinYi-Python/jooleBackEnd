package com.itlizeSession.joole.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.*;

/**
 * @ClassName Manufacturer
 * @Description TODO
 * @Author Yi Lin
 * @Date 5/11/22 01:23
 * @Version 1.0
 **/
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

//    @Column(name = "user_name", length = 20)
//    private String userName;
//
//    @Column(name = "password", length = 20)
//    private String password;

    @Column(name = "department")
    private String department;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "web_url")
    private String webUrl;


    @CreatedDate
    @Column(name = "create_time")
    private Timestamp createTime;


    @LastModifiedDate
    @Column(name = "update_time")
    private Timestamp updateTime;

    @JsonIgnore
    @OneToOne(targetEntity = Product.class, cascade = CascadeType.REMOVE, mappedBy = "manufacturerDetailId")
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
//        product.setManufacturerDetailId(this);
//    }
//
//    public void removeProducts(Product product){
//        if(!products.contains(product)){
//            return;
//        }
//        products.remove(product);
//        product.setManufacturerDetailId(null);
//    }

    @Override
    public String toString(){
        return "Manufacturer{" + "id=" + id + "}";
    }

    public String toJson(List<String> entries){
        String result = null;
        List<String> colsContent = new ArrayList<>();
        for(String entry: entries) {
            colsContent.add(entry);
        }
        result = "{" + String.join("," , colsContent) + "}";
        return String.format("{\"ManufacturerId\" : \"%d\", \"content\" : \"%s\"}" , getId(), result);

    }


    public Manufacturer() {
    }

    public Manufacturer(String department, String phone, String email, String webUrl) {
        this.department = department;
        this.phone = phone;
        this.email = email;
        this.webUrl = webUrl;
    }

    public Manufacturer(String department, String phone,
                        String email, String webUrl,
                        Timestamp createTime, Timestamp updateTime) {

        this.department = department;
        this.phone = phone;
        this.email = email;
        this.webUrl = webUrl;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

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
