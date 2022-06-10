package com.itlizeSession.joole.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;

/**
 * @ClassName User
 * @Description TODO
 * @Author Yi Lin
 * @Date 5/10/22 22:45
 * @Version 1.0
 **/

@EntityListeners(AuditingEntityListener.class)
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

//    private boolean isAdmin; // 0 is admin, 1 is user

    @CreatedDate
    @Column(name = "create_time")
    private Timestamp createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Timestamp updateTime;

    //problem!!!
//    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//    @JoinColumn(name = "Project_id")
//    private Project project;

    @JsonIgnore
    @OneToMany(targetEntity = Project.class, cascade = CascadeType.REMOVE, mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Project> projects = new HashSet<>();

    public Collection<Project> getProjects() {
        return projects;
    }

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
    }

    public void addProjects(Project project) {
        if(projects.contains(project)) {
            return;
        }
        projects.add(project);
        project.setUser(this);
    }

    public void removeProjects(Project project) {
        if(projects.contains(project)) {
            return;
        }
        projects.remove(project);
        project.setUser(null);
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public User(String username, String password, String name,
                String profilePictureUrl){
        this.username = username;
        this.password = password;
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
    }

    public User(String username, String password, String name,
                String profilePictureUrl, Timestamp createTime, Timestamp updateTime) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
