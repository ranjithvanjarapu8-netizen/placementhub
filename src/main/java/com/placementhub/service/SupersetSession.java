package com.placementhub.service;

public class SupersetSession {

    private final Long studentId;

    private final String username;

    private final String name;

    private final String uuid;

    private final String sessionKey;


    public SupersetSession(
            Long studentId,
            String username,
            String name,
            String uuid,
            String sessionKey
    ) {
        this.studentId = studentId;
        this.username = username;
        this.name = name;
        this.uuid = uuid;
        this.sessionKey = sessionKey;
    }


    public Long getStudentId() {
        return studentId;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public String getSessionKey() {
        return sessionKey;
    }
}