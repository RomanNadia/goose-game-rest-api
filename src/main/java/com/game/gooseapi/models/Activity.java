package com.game.gooseapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Activity {
    @Id
    private String name;

    private int satisfaction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }
}
