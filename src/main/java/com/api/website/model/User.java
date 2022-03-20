package com.api.website.model;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String email;
    private OffsetDateTime createdOn;
    private Collection<Role> roles;

    public User() {
    }

    public User(UUID id, String username, String email, OffsetDateTime createdOn,
                Collection<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdOn = createdOn;
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OffsetDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(OffsetDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
