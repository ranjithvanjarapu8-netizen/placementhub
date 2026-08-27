package com.placementhub.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "superset_student_sessions",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_superset_student_uuid",
                        columnNames = "student_uuid"
                )
        }
)
public class SupersetStudentSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "superset_user_id",
            nullable = false
    )
    private Long supersetUserId;

    @Column(
            name = "student_uuid",
            nullable = false,
            unique = true
    )
    private String studentUuid;

    @Column(
            nullable = false
    )
    private String username;

    private String name;

    @Column(
            name = "session_key",
            nullable = false,
            length = 2000
    )
    private String sessionKey;

    private LocalDateTime lastLoginAt;

    private LocalDateTime lastSyncedAt;


    public SupersetStudentSession() {
    }


    public Long getId() {
        return id;
    }

    public Long getSupersetUserId() {
        return supersetUserId;
    }

    public void setSupersetUserId(Long supersetUserId) {
        this.supersetUserId = supersetUserId;
    }

    public String getStudentUuid() {
        return studentUuid;
    }

    public void setStudentUuid(String studentUuid) {
        this.studentUuid = studentUuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public LocalDateTime getLastSyncedAt() {
        return lastSyncedAt;
    }

    public void setLastSyncedAt(LocalDateTime lastSyncedAt) {
        this.lastSyncedAt = lastSyncedAt;
    }
}