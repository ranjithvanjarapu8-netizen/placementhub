package com.placementhub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SupersetLoginResponse {

    private Long userId;

    private String username;

    private String name;

    private String emailHash;

    private String sessionKey;

    private String refreshToken;

    private String userProfilePhotoId;

    private String uuid;

    private List<String> userModes;

    private List<String> permissions;

    private Boolean emailVerified;

    private String message;

    private Boolean enableMfa;


    public SupersetLoginResponse() {
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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


    public String getEmailHash() {
        return emailHash;
    }

    public void setEmailHash(String emailHash) {
        this.emailHash = emailHash;
    }


    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }


    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


    public String getUserProfilePhotoId() {
        return userProfilePhotoId;
    }

    public void setUserProfilePhotoId(String userProfilePhotoId) {
        this.userProfilePhotoId = userProfilePhotoId;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public List<String> getUserModes() {
        return userModes;
    }

    public void setUserModes(List<String> userModes) {
        this.userModes = userModes;
    }


    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }


    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Boolean getEnableMfa() {
        return enableMfa;
    }

    public void setEnableMfa(Boolean enableMfa) {
        this.enableMfa = enableMfa;
    }
}