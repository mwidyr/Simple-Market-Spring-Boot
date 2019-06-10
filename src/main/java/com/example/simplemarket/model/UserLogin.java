package com.example.simplemarket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class UserLogin {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
    private String sessionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserDetail userDetail;

    public UserLogin() {
    }

    public UserLogin(String username, String password, String sessionId, UserDetail userDetail) {
        this.username = username;
        this.password = password;
        this.sessionId = sessionId;
        this.userDetail = userDetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserLogin{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", sessionId='").append(sessionId).append('\'');
        sb.append(", userDetail=").append(userDetail);
        sb.append('}');
        return sb.toString();
    }
}
