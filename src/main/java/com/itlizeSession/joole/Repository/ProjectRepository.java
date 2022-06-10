package com.itlizeSession.joole.Repository;

import com.itlizeSession.joole.Entity.Project;
import com.itlizeSession.joole.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findByUser(User user);
}
