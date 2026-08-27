package com.placementhub.repository;

import com.placementhub.entity.SupersetStudentSession;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupersetStudentSessionRepository
        extends JpaRepository<SupersetStudentSession, Long> {

    Optional<SupersetStudentSession> findByStudentUuid(
            String studentUuid
    );

    List<SupersetStudentSession> findAllBySessionKeyIsNotNull();
}