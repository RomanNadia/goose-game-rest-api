package com.game.gooseapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Detergent {
    @Id
    private String name;

    private int washingLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWashingLevel() {
        return washingLevel;
    }

    public void setWashingLevel(int washingLevel) {
        this.washingLevel = washingLevel;
    }
}
