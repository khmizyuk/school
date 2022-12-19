package org.example.repo;

import org.example.Entity.Staff;
import org.example.Entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends CrudRepository<Staff, String> {
    Optional<Staff> findByEmail(String s);
}
