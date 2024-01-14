package com.game.gooseapi.services;

import com.game.gooseapi.models.Goose;
import com.game.gooseapi.models.Hat;
import com.game.gooseapi.repositories.GooseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GooseService {
    @Autowired
    private GooseRepository gooseRepository;

    public void wearHat(Goose goose, Hat hat) {
        takeOffHat(goose);
        goose.setCurrentHat(hat);
        goose.setMaxHunger(goose.getMaxHunger() + hat.getHungerBonus());
        goose.setMaxHygiene(goose.getMaxHygiene() + hat.getHygieneBonus());
        goose.setMaxSatisfaction(goose.getMaxSatisfaction() + hat.getSatisfactionBonus());
        gooseRepository.save(goose);
    }


    private void takeOffHat(Goose goose) {
        goose.setMaxHunger(goose.getMaxHunger() - goose.getCurrentHat().getHungerBonus());
        goose.setMaxHygiene(goose.getMaxHygiene() - goose.getCurrentHat().getHygieneBonus());
        goose.setMaxSatisfaction(goose.getMaxSatisfaction() + goose.getCurrentHat().getSatisfactionBonus());
    }


}
