package com.game.gooseapi.repositories;

import com.game.gooseapi.models.Sessions;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Sessions, Integer> {
    Sessions findBySessionName(String sessionName);
//    Boolean existsBySessionName(String sessionName);
}
