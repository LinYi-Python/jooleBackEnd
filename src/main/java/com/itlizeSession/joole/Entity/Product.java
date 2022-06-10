package com.itlizeSession.joole.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

import com.itlizeSession.joole.Entity.ProductType;
import com.itlizeSession.joole.Entity.TechnicalDetail;
import com.itlizeSession.joole.Entity.Manufacturer;
import com.itlizeSession.joole.Entity.Sale;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.*;

/**
 * @ClassName Product
 * @Description TODO
 * @Author Yi Lin
 * @Date 5/11/22 00:22
 * @Version 1.0
 **/
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(targetEntity = ProductType.class, cascade = CascadeType.DETACH)
//    @JoinColumn(name = "product_type_id")
    private ProductType productType;


//    @ManyToone(tragetE = Story)
//    @Joins name = story_id
//    private Story story


//    @Column(name = "product_type_id", length = 20)
//    private Integer productTypeId;

//    @ManyToOne(targetEntity = TechnicalDetail.class, cascade = CascadeType.DETACH)
//    @JoinColumn(name = "technical_detail_id")
//    private TechnicalDetail technicalDetail;

    @JsonIgnore
    @OneToMany(targetEntity = TechnicalDetail.class, cascade = CascadeType.REMOVE, mappedBy = "product")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<TechnicalDetail> technicalDetails = new ArrayList<>();

    public List<TechnicalDetail> getTechnicalDetails(){
        return technicalDetails;
    }

    public void addTechnicalDetails(TechnicalDetail technicalDetail) {
        if(technicalDetails.contains(technicalDetail)){
            return;
        }
        technicalDetails.add(technicalDetail);
        technicalDetail.setProduct(this);
    }

    public void removeTechnicalDetails(TechnicalDetail technicalDetail){
        if(technicalDetails.contains(technicalDetail)){
            return;
        }
        technicalDetails.remove(technicalDetail);
        technicalDetail.setProduct(this);
    }

    @JsonIgnore
    @OneToMany(targetEntity = ProjectProduct.class, cascade = CascadeType.REMOVE, mappedBy = "product")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<ProjectProduct> projectProducts = new HashSet<>();

    public Collection<ProjectProduct> getProjectProducts() {
        return projectProducts;
    }

    public void setProjectProducts(Collection<ProjectProduct> projectProducts) {
        this.projectProducts = projectProducts;
    }

    public void addProjectProducts(ProjectProduct projectProduct) {
        if(projectProducts.contains(projectProduct)) {
            return;
        }
        projectProducts.add(projectProduct);
        projectProduct.setProduct(this);
    }

    public void removeProjectProducts(ProjectProduct projectProduct) {
        if(projectProducts.contains(projectProduct)) {
            return;
        }
        projectProducts.remove(projectProduct);
        projectProduct.setProduct(null);
    }


//    @Column(name = "technical_detail_id", length = 20)
//    private Integer technicalDetailId;

    @OneToOne(targetEntity = Manufacturer.class, cascade = CascadeType.DETACH)
//    @JoinColumn(name = "manufacturer_detail_id")
    private Manufacturer manufacturerDetailId;

//    @Column(name = "manufacturer_detail_id", length = 20)
//    private Integer manufacturerDetailId;

    @Column(name = "model_year")
    private Integer modelYear;


    @OneToOne(targetEntity = Sale.class, cascade = CascadeType.DETACH)
//    @JoinColumn(name = "sale_id")
    private Sale sale;

    @Column(name = "brand")
    private String brand;

    @CreatedDate
    @Column(name = "create_time")
    private Timestamp createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Timestamp updateTime;

//    @JsonIgnore
//    @OneToMany(targetEntity = ProjectProduct.class, cascade = CascadeType.ALL, mappedBy = "product")
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<ProjectProduct> ProjectProducts = new ArrayList<>();



    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productType=" + productType +
                ", manufacturerDetailId=" + manufacturerDetailId +
                ", modelYear=" + modelYear +
                ", sale=" + sale +
                ", brand='" + brand + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public String toJson() {
        return "{" +
                "\"id\" : \"" + id +
                "\" , \"name\": \"" + name +
                "\" , \"productType\": \"" + productType +
                "\" , \"manufacturerDetailId\": \"" + manufacturerDetailId +
                "\" , \"modelYear\": \"" + modelYear +
                "\" , \"sale\": \"" + sale +
                "\" , \"brand\": \"" + brand +
                "\" , \"createTime\": \"" + createTime +
                "\" , \"updateTime\": \"" + updateTime +
                "\"}";
    }

    public Product() {
    }

    public Product(String name, Integer modelYear, String brand) {
        this.name = name;
        this.modelYear = modelYear;
        this.brand = brand;
    }

    public Product(String name, Integer modelYear, String brand, Timestamp createTime, Timestamp updateTime) {
        this.name = name;
        this.modelYear = modelYear;
        this.brand = brand;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

//    public TechnicalDetail getTechnicalDetail() {
//        return technicalDetail;
//    }
//
//    public void setTechnicalDetail(TechnicalDetail technicalDetail) {
//        this.technicalDetail = technicalDetail;
//    }

    public Manufacturer getManufacturerDetailId() {
        return manufacturerDetailId;
    }

    public void setManufacturerDetailId(Manufacturer manufacturerDetailId) {
        this.manufacturerDetailId = manufacturerDetailId;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public void setTechnicalDetails(List<TechnicalDetail> technicalDetails) {
        this.technicalDetails = technicalDetails;
    }

//    public List<ProjectProduct> getProjectProducts() {
//        return ProjectProducts;
//    }
//
//    public void setProjectProducts(List<ProjectProduct> projectProducts) {
//        ProjectProducts = projectProducts;
//    }
}
