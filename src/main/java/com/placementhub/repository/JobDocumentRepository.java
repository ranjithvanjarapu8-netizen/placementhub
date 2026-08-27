package com.placementhub.repository;

import com.placementhub.entity.JobDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobDocumentRepository
        extends JpaRepository<JobDocument, Long> {

    List<JobDocument> findByJobProfileId(Long jobProfileId);

    Optional<JobDocument> findByJobProfileIdAndSupersetDocumentId(
            Long jobProfileId,
            String supersetDocumentId
    );
}