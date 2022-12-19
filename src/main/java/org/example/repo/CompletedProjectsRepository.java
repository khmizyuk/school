package org.example.repo;

import org.example.Entity.Campus;
import org.example.Entity.CompletedProjects;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedProjectsRepository extends CrudRepository<CompletedProjects, String> {
}
