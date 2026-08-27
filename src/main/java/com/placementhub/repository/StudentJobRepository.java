package com.placementhub.repository;

import com.placementhub.entity.StudentJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentJobRepository extends JpaRepository<StudentJob, Long> {

    Optional<StudentJob> findByStudentIdAndJobProfileId(
            Long studentId,
            Long jobProfileId
    );

    List<StudentJob> findByStudentId(Long studentId);
    
}