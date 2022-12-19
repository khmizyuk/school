package org.example.repo;

import org.example.Entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
    Optional<Student> findByEmail(String s);
}
