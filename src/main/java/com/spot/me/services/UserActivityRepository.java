package com.spot.me.services;

import com.spot.me.entities.ActivityName;
import com.spot.me.entities.User;
import com.spot.me.entities.UserActivity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by BHarris on 4/5/17.
 */
public interface UserActivityRepository extends CrudRepository<UserActivity, String> {
    List<UserActivity> findAllByUserId(String id);

}
