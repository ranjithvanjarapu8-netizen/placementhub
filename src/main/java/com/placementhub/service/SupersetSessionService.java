package com.placementhub.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SupersetSessionService {

    private final Map<Long, SupersetSession> sessions =
            new ConcurrentHashMap<>();


    public void createSession(
            SupersetSession session
    ) {

        sessions.put(
                session.getStudentId(),
                session
        );
    }


    public SupersetSession getSession(
            Long studentId
    ) {

        return sessions.get(studentId);
    }


    public void removeSession(
            Long studentId
    ) {

        sessions.remove(studentId);
    }


    public boolean hasSession(
            Long studentId
    ) {

        return sessions.containsKey(studentId);
    }
}