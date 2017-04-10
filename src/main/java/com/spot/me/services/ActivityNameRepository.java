package com.spot.me.services;

import com.spot.me.entities.ActivityName;
import org.springframework.data.repository.CrudRepository;

public interface ActivityNameRepository extends CrudRepository<ActivityName, String> {
    ActivityName findFirstByActivityName(String name);
}
