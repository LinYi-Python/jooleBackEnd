package com.itlizeSession.joole.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @ClassName Project
 * @Description TODO
 * @Author Yi Lin
 * @Date 5/11/22 00:15
 * @Version 1.0
 **/

@EntityListeners(AuditingEntityListener.class)
@Entity(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

//    @Column(name = "user_id", length = 20)
//    private Integer user_id;
    @Column(name = "project_name")
    private String projectName;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.DETACH)
//    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(targetEntity = ProjectProduct.class, cascade = CascadeType.REMOVE, mappedBy = "project")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<ProjectProduct> projectProducts = new HashSet<>();

    @CreatedDate
    @Column(name = "create_time")
    private Timestamp createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Timestamp updateTime;

    public Project() {
    }

    public Project(String projectName) {
        this.projectName = projectName;
    }

    public Project(String projectName, User user) {
        this.projectName = projectName;
        this.user = user;
    }

    public Project(String projectName, User user, Timestamp createTime, Timestamp updateTime) {

        this.projectName = projectName;
        this.user = user;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }


    public void addProjectProducts(ProjectProduct projectProduct) {
        if(projectProducts.contains(projectProduct)) {
            return;
        }
        projectProducts.add(projectProduct);
        projectProduct.setProject(this);
    }

    public void removeProjectProducts(ProjectProduct projectProduct) {
        if(projectProducts.contains(projectProduct)) {
            return;
        }
        projectProducts.remove(projectProduct);
        projectProduct.setProject(null);
    }


    public String getProjectName() {
        return projectName;
    }

    public User getUser() {
        return user;
    }

    public Collection<ProjectProduct> getProjectProducts() {
        return projectProducts;
    }

    public void setProjectProducts(Collection<ProjectProduct> projectProducts) {
        this.projectProducts = projectProducts;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "Project{" +
                "ProjectId=" + id +
                ", user=" + user +
                ", projectName=" + projectName +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
