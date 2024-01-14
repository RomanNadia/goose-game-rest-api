package com.game.gooseapi.controllers;

import com.game.gooseapi.models.Detergent;
import com.game.gooseapi.repositories.DetergentRepository;
import com.game.gooseapi.response.MyResponseObject;
import com.game.gooseapi.response.OperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DetergentController {
    @Autowired
    private DetergentRepository detergentRepository;

    @GetMapping(path="/detergents")
    public @ResponseBody MyResponseObject<Iterable<Detergent>> getAllDetergentss() {
        Iterable<Detergent> detergents = detergentRepository.findAll();
        MyResponseObject<Iterable<Detergent>> response = new MyResponseObject<>(detergents, OperationStatus.SUCCESSFUL_OPERATION);
        return response;
    }
}
