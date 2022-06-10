package com.itlizeSession.joole.Controller;

import com.itlizeSession.joole.Entity.*;
import com.itlizeSession.joole.Service.ProjectProductService;
import com.itlizeSession.joole.Service.ProjectService;
import com.itlizeSession.joole.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.sql.Timestamp;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/projectController")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectProductService projectProductService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/getAllProject")
    public ResponseEntity<?> getAllProject(){
        Collection<Project> result = projectService.findAll();
        List<Project> res = result.stream().collect(toList());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/getOneProject")
    public ResponseEntity<?> findOneById(@RequestParam("id") Integer id) {
        return new ResponseEntity<>(projectService.findOneById(id), HttpStatus.OK);
    }

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
//    @PostMapping("/save/")
//    public ResponseEntity<?> save(@RequestParam("id") Integer id,
//                                  @RequestParam("user_id") Integer user_id) {
//        User user = userService.findUserById(user_id);
//        Timestamp createTime = new Timestamp(System.currentTimeMillis());
//        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
//        Project project = new Project(id, user, createTime, updateTime);
//
//        return new ResponseEntity<>(projectService.save(project), HttpStatus.OK);
//    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam("projectName") String projectName,
                                    @RequestParam("user_Id") Integer user_Id) {
        User user = userService.findUserById(user_Id);
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        Project project = new Project(projectName, user, createTime, updateTime);

        return new ResponseEntity<>(projectService.create(project, user), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id) {
        Project project = projectService.findOneById(id);
        return new ResponseEntity<>(projectService.delete(project), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam("id") Integer id, @RequestParam("projectName") String projectName
                                    ) {

        Project project = projectService.findOneById(id);
        if(projectName != null) {
            project.setProjectName(projectName);
        }
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        project.setUpdateTime(updateTime);

//        Project project = new Project(id, user, createTime, updateTime);

        return new ResponseEntity<>(projectService.update(project), HttpStatus.OK);
    }

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
//    @PostMapping("/deleteAll")
//    public ResponseEntity<?> deleteAll() {
//        return new ResponseEntity<>(projectService.deleteAll(), HttpStatus.OK);
//    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/findProductsFromProject")
    public ResponseEntity<?> findProductsFromProject(@RequestParam("id") Integer id) {
        List<Product> result = projectProductService.findProductsFromProject(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/findProjectsFromUser")
    public ResponseEntity<?> findProjectsFromUser(@RequestParam("id") Integer id) {
        List<Project> result = projectService.findProjectsFromUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/addProductToProject")
    public ResponseEntity<?> addProductToProject(@RequestParam("product_id")Integer productId,
                                                 @RequestParam("project_id")Integer projectId){
        boolean result = projectProductService.addProductFromProject(productId, projectId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/deleteProductFromProject")
    public ResponseEntity<?> deleteProductFromProject(@RequestParam("product_id")Integer productId,
                                                 @RequestParam("project_id")Integer projectId){
        boolean result = projectProductService.deleteProductFromProject(productId, projectId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/addProjectToUser")
    public ResponseEntity<?> addProjectToUser(@RequestParam("project_id")Integer projectId, @RequestParam("user_id")Integer userId){
        boolean result = projectService.addProjectFromUser(projectId, userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/deleteProjectFromUser")
    public ResponseEntity<?> deleteProjectFromUser(@RequestParam("project_id")Integer projectId, @RequestParam("user_id")Integer userId){
        boolean result = projectService.deleteProjectFromUser(projectId, userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
