package com.game.gooseapi.controllers;

import com.game.gooseapi.models.Hat;
import com.game.gooseapi.repositories.HatRepository;
import com.game.gooseapi.response.MyResponseObject;
import com.game.gooseapi.response.OperationStatus;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class HatController {
    private final HatRepository hatRepository;

    @Autowired
    public HatController(HatRepository hatRepository) {
        this.hatRepository = hatRepository;
    }


    @GetMapping(path="hats/get-hats-which-name-contains")
    public @ResponseBody
    MyResponseObject<ArrayList<Hat>> getAllHatsByString(@PathParam("string") String string) {
        Iterable<Hat> hats = hatRepository.findAll();
        ArrayList<Hat> hatsWithString = new ArrayList<>();

        for(Hat hat: hats) {
            if(hat.getName().contains(string)) {
                hatsWithString.add(hat);
            }
        }

        if(hatsWithString.isEmpty()) {
            return new MyResponseObject<>(OperationStatus.NOT_FOUND_OBJECT);
        }

        return new MyResponseObject(hatsWithString, OperationStatus.SUCCESSFUL_OPERATION);
    }
}
