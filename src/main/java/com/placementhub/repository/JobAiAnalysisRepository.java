package com.placementhub.repository;

import com.placementhub.entity.JobAiAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobAiAnalysisRepository
        extends JpaRepository<JobAiAnalysis, Long> {

    Optional<JobAiAnalysis> findByJobProfileId(Long jobProfileId);
}