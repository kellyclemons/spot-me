package com.spot.me.services;

import com.spot.me.entities.UserAgeRange;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by BHarris on 4/6/17.
 */
public interface UserAgeRangeRepository extends CrudRepository<UserAgeRange, Integer> {
}
