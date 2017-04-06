package com.spot.me.services;

import com.spot.me.entities.UserAvailability;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by BHarris on 4/6/17.
 */
public interface UserAvailabilityRepository extends CrudRepository<UserAvailability, Integer> {
}
