package com.game.gooseapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Sessions {
    @Id
    private String sessionName;

    @OneToMany
    private Set<Goose> geeseSet = new HashSet<>();


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

}
