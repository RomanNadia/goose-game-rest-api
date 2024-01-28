package com.game.gooseapi.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Goose {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int maxHunger;
    private int currentHunger;

    private int maxHygiene;
    private int currentHygiene;

    private int maxSatisfaction;
    private int currentSatisfaction;

    private int maxHealth;
    private int currentHealth;

    private long lastUpdateTime;

    @ManyToOne
    @JoinColumn(name = "currentHat")
    private Hat currentHat;

    @ManyToOne
    @JoinColumn(name = "sessionId")
    private Sessions sessions;

    public Goose() {
    }

    public Goose(Long id, String name, Hat currentHat, Sessions sessions) {
        this.id = id;
        this.name = name;
        this.currentHat = currentHat;
        this.sessions = sessions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHunger() {
        return maxHunger;
    }

    public void setMaxHunger(int maxHunger) {
        this.maxHunger = maxHunger;
    }

    public int getCurrentHunger() {
        return currentHunger;
    }

    public void setCurrentHunger(int currentHunger) {
        this.currentHunger = currentHunger;
    }

    public int getMaxHygiene() {
        return maxHygiene;
    }

    public void setMaxHygiene(int maxHygiene) {
        this.maxHygiene = maxHygiene;
    }

    public int getCurrentHygiene() {
        return currentHygiene;
    }

    public void setCurrentHygiene(int currentHygiene) {
        this.currentHygiene = currentHygiene;
    }

    public int getMaxSatisfaction() {
        return maxSatisfaction;
    }

    public void setMaxSatisfaction(int maxSatisfaction) {
        this.maxSatisfaction = maxSatisfaction;
    }

    public int getCurrentSatisfaction() {
        return currentSatisfaction;
    }

    public void setCurrentSatisfaction(int currentSatisfaction) {
        this.currentSatisfaction = currentSatisfaction;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Sessions getSessions() {
        return sessions;
    }

    public void setSessions(Sessions sessions) {
        this.sessions = sessions;
    }

    public Hat getCurrentHat() {
        return currentHat;
    }

    public void setCurrentHat(Hat currentHat) {
        this.currentHat = currentHat;
    }
}
