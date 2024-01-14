package com.game.gooseapi.repositories;

import com.game.gooseapi.models.Goose;
import com.game.gooseapi.models.Sessions;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface GooseRepository extends CrudRepository<Goose, Integer> {
    Goose findByName(String name);
    List<Goose> findBySessions(Sessions sessions);
}
