package com.game.gooseapi.controllers;

import com.game.gooseapi.config.ApplicationConfig;
import com.game.gooseapi.models.Goose;
import com.game.gooseapi.models.Hat;
import com.game.gooseapi.repositories.GooseRepository;
import com.game.gooseapi.repositories.HatRepository;
import com.game.gooseapi.repositories.SessionRepository;
import com.game.gooseapi.response.MyResponseObject;
import com.game.gooseapi.response.OperationStatus;
import com.game.gooseapi.services.GooseService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class GooseController {
    private final GooseRepository gooseRepository;
    private final SessionRepository sessionRepository;
    private final HatRepository hatRepository;
    private final GooseService gooseService;

    @Autowired
    public GooseController(GooseRepository gooseRepository, SessionRepository sessionRepository,
                           HatRepository hatRepository, GooseService gooseService) {
        this.gooseRepository = gooseRepository;
        this.sessionRepository = sessionRepository;
        this.hatRepository = hatRepository;
        this.gooseService = gooseService;
    }

    @PostMapping(path="session/{sessionName}/goose/{gooseName}")
    public @ResponseBody
    OperationStatus createNewGoose(@PathVariable String sessionName, @PathVariable String gooseName, @RequestBody Goose goose) {
        if(isSessionExist(sessionName)) {
            if (!isSuchGooseExistInSession(gooseName, sessionName)) {
                goose.setName(gooseName);
                goose.setSessions(sessionRepository.findBySessionName(sessionName));

                goose.setMaxHunger(getRightCharacteristics(goose.getMaxHunger(), ApplicationConfig.MAX_HUNGER));
                goose.setCurrentHunger(getRightCharacteristics(goose.getMaxHunger(), ApplicationConfig.MAX_HUNGER));

                goose.setMaxHygiene(getRightCharacteristics(goose.getMaxHygiene(), ApplicationConfig.MAX_HYGIENE));
                goose.setCurrentHygiene(getRightCharacteristics(goose.getMaxHygiene(), ApplicationConfig.MAX_HYGIENE));

                goose.setMaxSatisfaction(
                        getRightCharacteristics(goose.getMaxSatisfaction(), ApplicationConfig.MAX_SATISFACTION));
                goose.setCurrentSatisfaction(
                        getRightCharacteristics(goose.getMaxSatisfaction(), ApplicationConfig.MAX_SATISFACTION));

                goose.setMaxHealth(
                        getRightCharacteristics(goose.getMaxSatisfaction(), ApplicationConfig.MAX_SATISFACTION));
                goose.setCurrentHealth(
                        getRightCharacteristics(goose.getMaxSatisfaction(), ApplicationConfig.MAX_SATISFACTION));

                Date date = new Date();
                long timeMilliNow = date.getTime();
                goose.setLastUpdateTime(timeMilliNow);

                //setting default hat (no hat) during creation
                goose.setCurrentHat(hatRepository.findHatById(ApplicationConfig.ID_OF_DEFAULT_CURRENT_HAT));

                gooseRepository.save(goose);
                return OperationStatus.SAVED;
            } else {
                return OperationStatus.ALREADY_EXIST;
            }
        } else {
            return OperationStatus.FAILED;
        }
    }


    private int getRightCharacteristics(int maxCharect, int defaultCharacteristic) {
        if(maxCharect <= 0) {
            return defaultCharacteristic;
        } else {
            return maxCharect;
        }
    }


    private boolean isSessionExist(String name) {
        return sessionRepository.findBySessionName(name) != null;
    }


    private boolean isSuchGooseExistInSession(String gooseName, String sessionName) {
        Goose goose = gooseRepository.findByName(gooseName);
        if(goose == null) {
            return false;
        } else {
            return goose.getSessions().getSessionName().equals(sessionName);
        }
    }


    @GetMapping(path="/geese")
    public @ResponseBody
    MyResponseObject<Iterable<Goose>> getAllGooses() {
        Iterable<Goose> geese = gooseRepository.findAll();
        MyResponseObject<Iterable<Goose>> response;
        if(geese == null) {
            response = new MyResponseObject<>(OperationStatus.NO_SESSIONS);
        } else {
            response = new MyResponseObject<>(geese, OperationStatus.SUCCESSFUL_OPERATION);
        }
        return response;
    }


    @GetMapping(path="session/{sessionName}/goose/{gooseName}")
    public @ResponseBody
    MyResponseObject<Goose> getGooseByName(@PathVariable String sessionName, @PathVariable String gooseName) {
        if(isSessionExist(sessionName)) {
            if(isSuchGooseExistInSession(gooseName, sessionName)) {
                List<Goose> geeseInSession
                        = gooseRepository.findBySessions(sessionRepository.findBySessionName(sessionName));
                Goose responseGoose = new Goose();
                    for(Goose goose: geeseInSession) {
                        if (goose.getName().equals(gooseName)){
                            responseGoose = goose;
                        }
                    }
                return new MyResponseObject<>(responseGoose, OperationStatus.SUCCESSFUL_OPERATION);
            } else {
                return new MyResponseObject<>(OperationStatus.NOT_FOUND_OBJECT);
            }
        } else {
            return new MyResponseObject<>(OperationStatus.FAILED);
        }
    }


    @GetMapping(path="session/{sessionName}/geese")
    public @ResponseBody
    MyResponseObject<List<Goose>> getAllGeeseInSession(@PathVariable String sessionName) {
        if(isSessionExist(sessionName)) {
            List<Goose> geeseInSession = gooseRepository.findBySessions(sessionRepository.findBySessionName(sessionName));
            return new MyResponseObject<>(geeseInSession, OperationStatus.SUCCESSFUL_OPERATION);
        } else {
            return new MyResponseObject<>(OperationStatus.FAILED);
        }
    }


    @PostMapping(path="session/{sessionName}/goose/{gooseName}/put-hat")
    public @ResponseBody
    OperationStatus wearHat(@PathVariable String sessionName, @PathVariable String gooseName,
                                   @PathParam("hatId") int hatId) {
        if(isSessionExist(sessionName)) {
            if(isSuchGooseExistInSession(gooseName, sessionName) && hatRepository.findHatById(hatId) != null) {
                Hat hat = hatRepository.findHatById(hatId);
                Goose goose = gooseRepository.findByName(gooseName);

                gooseService.wearHat(goose, hat);

                return OperationStatus.SUCCESSFUL_OPERATION;
            } else {
                return OperationStatus.NOT_FOUND_OBJECT;
            }
        } else {
            return OperationStatus.FAILED;
        }
    }



}
