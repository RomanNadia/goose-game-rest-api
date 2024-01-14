package com.game.gooseapi.controllers;

import com.game.gooseapi.error.handling.ResourceNotFoundException;
import com.game.gooseapi.models.Activity;
import com.game.gooseapi.repositories.ActivityRepository;
import com.game.gooseapi.response.MyResponseObject;
import com.game.gooseapi.response.OperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ActivityController {
    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping(path="/activities")
    public @ResponseBody MyResponseObject<Iterable<Activity>> getAllActivities() {
        Iterable<Activity> activities = activityRepository.findAll();
        MyResponseObject<Iterable<Activity>> response = new MyResponseObject<>(activities, OperationStatus.SUCCESSFUL_OPERATION);
        return response;
    }
}
