package com.game.gooseapi.controllers;

import com.game.gooseapi.models.Sessions;
import com.game.gooseapi.repositories.SessionRepository;
import com.game.gooseapi.response.MyResponseObject;
import com.game.gooseapi.response.OperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SessionController {
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @PostMapping(path="session/{sessionName}")
    public @ResponseBody
    OperationStatus createNewSession (@PathVariable String sessionName) {
        if(sessionRepository.findBySessionName(sessionName) == null) {
            Sessions sessions = new Sessions();
            sessions.setSessionName(sessionName);
            sessionRepository.save(sessions);
            return OperationStatus.SAVED;
        } else {
            return OperationStatus.ALREADY_EXIST;
        }
    }


    @GetMapping(path="/sessions")
    public @ResponseBody MyResponseObject<Iterable<Sessions>> getAllSessions() {
        Iterable<Sessions> sessions = sessionRepository.findAll();
        MyResponseObject<Iterable<Sessions>> response;
        if(sessions == null) {
            response = new MyResponseObject<>(OperationStatus.NO_SESSIONS);
        } else {
            response = new MyResponseObject<>(sessions, OperationStatus.SUCCESSFUL_OPERATION);
        }
        return response;
    }


    @GetMapping(path="/session/{sessionName}")
    public @ResponseBody MyResponseObject<Sessions> getSessionsByName(@PathVariable String sessionName) {
        Sessions session = sessionRepository.findBySessionName(sessionName);
        MyResponseObject<Sessions> response;
        if(session == null) {
            response = new MyResponseObject<>(OperationStatus.NOT_FOUND_OBJECT);
        } else {
            response = new MyResponseObject<>(session, OperationStatus.SUCCESSFUL_OPERATION);
        }
        return response;
    }
}
