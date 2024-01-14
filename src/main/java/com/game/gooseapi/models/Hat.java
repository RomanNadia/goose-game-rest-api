package com.game.gooseapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Hat {
    @Id
    private int id;

    private String name;
    private int hungerBonus;
    private int hygieneBonus;
    private int satisfactionBonus;

    @OneToMany
    private Set<Goose> geeseSet = new HashSet<>();

    public Hat() {
    }

    public Hat(int id, String name, int hungerBonus, int hygieneBonus, int satisfactionBonus) {
        this.id = id;
        this.name = name;
        this.hungerBonus = hungerBonus;
        this.hygieneBonus = hygieneBonus;
        this.satisfactionBonus = satisfactionBonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hat hat = (Hat) o;
        return id == hat.id && hungerBonus == hat.hungerBonus && hygieneBonus == hat.hygieneBonus && satisfactionBonus == hat.satisfactionBonus && Objects.equals(name, hat.name) && Objects.equals(geeseSet, hat.geeseSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hungerBonus, hygieneBonus, satisfactionBonus, geeseSet);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHungerBonus() {
        return hungerBonus;
    }

    public void setHungerBonus(int hungerBonus) {
        this.hungerBonus = hungerBonus;
    }

    public int getHygieneBonus() {
        return hygieneBonus;
    }

    public void setHygieneBonus(int hygieneBonus) {
        this.hygieneBonus = hygieneBonus;
    }

    public int getSatisfactionBonus() {
        return satisfactionBonus;
    }

    public void setSatisfactionBonus(int satisfactionBonus) {
        this.satisfactionBonus = satisfactionBonus;
    }

    public Set<Goose> getGeeseSet() {
        return geeseSet;
    }

    public void setGeeseSet(Set<Goose> geeseSet) {
        this.geeseSet = geeseSet;
    }
}
