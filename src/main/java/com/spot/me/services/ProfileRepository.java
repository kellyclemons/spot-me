package com.spot.me.services;

import com.spot.me.entities.Profile;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by BHarris on 4/7/17.
 */
public interface ProfileRepository extends CrudRepository<Profile, Integer> {
}
