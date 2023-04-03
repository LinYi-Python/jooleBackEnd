package com.itlizeSession.joole.Mockito;

import com.itlizeSession.joole.Entity.Project;
import com.itlizeSession.joole.Entity.Product;
import com.itlizeSession.joole.Entity.Project;
import com.itlizeSession.joole.Entity.User;
import com.itlizeSession.joole.Service.ProjectService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import com.itlizeSession.joole.Repository.ProjectRepository;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.spy;

/**
 * @ClassName ProjectApplicationTest
 * @Description TODO
 * @Author
 * @Date 7/20/22 15:17
 * @Version 1.0
 **/
@RunWith(MockitoJUnitRunner.class)
class ProjectApplicationTest {

    @Autowired
    private ProjectApplication projectApplication;
    private ProjectService projectService;
    private ProjectRepository projectRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        projectApplication = new ProjectApplication();
        ProjectSpy project = new ProjectSpy();
        projectService =  spy(project);
        projectApplication.setProjectService(projectService);
    }

    class ProjectSpy implements ProjectService{


        @Override
        public Project findOneById(Integer Id) {
            return projectRepository.findById(Id).orElse(null);
        }

        @Override
        public Collection<Project> findAll() {
            return projectRepository.findAll();
        }

        @Override
        public Project save(Project project) {
            return null;
        }

        @Override
        public boolean create(Project project, User user) {
            return false;
        }

        @Override
        public boolean delete(Project project) {
            return false;
        }

        @Override
        public boolean update(Project project) {
            return false;
        }

        @Override
        public boolean deleteAll() {
            return false;
        }

        @Override
        public Project get(Integer id) {
            return null;
        }

        @Override
        public List<Product> findProductsFromProject(Integer id) {
            return null;
        }

        @Override
        public List<Project> findProjectsFromUser(Integer id) {
            return null;
        }

        @Override
        public boolean addProductFromProject(Integer productId, Integer projectId) {
            return false;
        }

        @Override
        public boolean deleteProductFromProject(Integer productId, Integer projectId) {
            return false;
        }

        @Override
        public boolean addProjectFromUser(Integer projectId, Integer userId) {
            return false;
        }

        @Override
        public boolean deleteProjectFromUser(Integer projectId, Integer userId) {
            return false;
        }
    }


    @Test
    void findOneById() {
        Assert.assertNotNull(projectApplication.findOneById(55));
    }

    @Test
    void findAll() {
    }

//    @Test
//    void findProductsFromProject() {
//    }
//
//    @Test
//    void findProjectsFromUser() {
//    }
//
//    @Test
//    void addProductFromProject() {
//    }
//
//    @Test
//    void deleteProductFromProject() {
//    }
//
//    @Test
//    void addProjectFromUser() {
//    }
//
//    @Test
//    void deleteProjectFromUser() {
//    }
}