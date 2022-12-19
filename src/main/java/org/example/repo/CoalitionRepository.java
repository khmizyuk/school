package org.example.repo;

import org.example.Entity.Coalition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoalitionRepository extends CrudRepository<Coalition, String> {
}
