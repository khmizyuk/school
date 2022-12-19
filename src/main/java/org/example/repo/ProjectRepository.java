package org.example.repo;

import org.example.Entity.Campus;
import org.example.Entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String> {
}
