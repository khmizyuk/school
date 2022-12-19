package org.example.repo;

import org.example.Entity.Campus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampusRepository extends CrudRepository<Campus, String> {
}
