package com.spot.me.services;

import com.spot.me.entities.UserActivity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by BHarris on 4/5/17.
 */
public interface UserActivityRepository extends CrudRepository<UserActivity, Integer> {
}
