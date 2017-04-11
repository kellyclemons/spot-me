package com.spot.me.services;

import com.spot.me.entities.UserAvailability;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by BHarris on 4/6/17.
 */
public interface UserAvailabilityRepository extends CrudRepository<UserAvailability, String> {
    List<UserAvailability> findDayByUserId(String id);
}
