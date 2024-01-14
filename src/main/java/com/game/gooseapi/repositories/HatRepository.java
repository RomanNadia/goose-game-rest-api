package com.game.gooseapi.repositories;

import com.game.gooseapi.models.Hat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HatRepository extends CrudRepository<Hat, Integer> {
    Hat findHatById(int id);
}
