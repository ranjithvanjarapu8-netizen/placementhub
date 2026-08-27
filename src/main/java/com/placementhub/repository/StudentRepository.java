package com.placementhub.repository;

import com.placementhub.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);
    Optional<Student> findBySupersetStudentId(String supersetStudentId);
    boolean existsByEmail(String email);
    
}