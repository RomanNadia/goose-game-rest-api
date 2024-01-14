package com.game.gooseapi.controllers;

import com.game.gooseapi.models.Food;
import com.game.gooseapi.models.Goose;
import com.game.gooseapi.repositories.FoodRepository;
import com.game.gooseapi.response.MyResponseObject;
import com.game.gooseapi.response.OperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FoodController {
    @Autowired
    private FoodRepository foodRepository;

    @GetMapping(path = "/foods")
    public @ResponseBody
    MyResponseObject<Iterable<Food>> getAllFoods() {
        Iterable<Food> foods = foodRepository.findAll();
        MyResponseObject<Iterable<Food>> response = new MyResponseObject<>(foods, OperationStatus.SUCCESSFUL_OPERATION);
        return response;
    }
}