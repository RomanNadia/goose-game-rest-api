package com.game.gooseapi.repositories;

import com.game.gooseapi.models.Activity;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepository extends CrudRepository<Activity, Integer> {
}
