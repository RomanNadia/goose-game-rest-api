package com.game.gooseapi.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Sessions {
    @Id
    private String sessionName;

    @OneToMany
    private Set<Goose> geeseSet = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    public Sessions() {
    }

    public Sessions(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Set<Goose> getGeeseSet() {
        return geeseSet;
    }

    public void setGeeseSet(Set<Goose> geeseSet) {
        this.geeseSet = geeseSet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
