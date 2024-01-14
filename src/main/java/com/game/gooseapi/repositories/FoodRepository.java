package com.game.gooseapi.repositories;

import com.game.gooseapi.models.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, Integer> {
}
