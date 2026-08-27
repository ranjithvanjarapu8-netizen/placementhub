package com.placementhub.repository;

import com.placementhub.entity.JobProfile;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobProfileRepository
        extends JpaRepository<JobProfile, Long> {

    Optional<JobProfile> findBySupersetJobProfileId(
            String supersetJobProfileId
    );

    List<JobProfile> findByStatus(
            String status
    );

    List<JobProfile> findAllByOrderByApplicationDeadlineAsc();
}