package com.itlizeSession.joole.Repository;

import com.itlizeSession.joole.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by yang shu
 * 2018-03-11 21:38
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    Optional<User> findById(Integer id);
    

    User findUserByUsername(String userName);
    //findUserByUsername

//    //addProject
//    User addProject(Project project, User user);
    Optional<User> findByUsername(String username);


}
