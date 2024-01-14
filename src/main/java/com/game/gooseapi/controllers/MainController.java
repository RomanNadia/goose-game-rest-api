package com.game.gooseapi.controllers;

import com.game.gooseapi.error.handling.ResourceNotFoundException;
import com.game.gooseapi.models.Sessions;
import com.game.gooseapi.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @RequestMapping("**")
    public void handleUnknownPaths() {
        throw new ResourceNotFoundException();
    }

    @GetMapping("/")
    public String getHome() {
        return "home";
    }

}
