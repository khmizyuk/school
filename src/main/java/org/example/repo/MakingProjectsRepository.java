package org.example.repo;

import org.example.Entity.Campus;
import org.example.Entity.MakingProjects;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakingProjectsRepository extends CrudRepository<MakingProjects, String> {
}
