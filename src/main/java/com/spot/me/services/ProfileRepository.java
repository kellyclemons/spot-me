package com.spot.me.services;

import com.spot.me.entities.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by BHarris on 4/7/17.
 */
public interface ProfileRepository extends CrudRepository<Profile, String> {
    Profile findFirstByUserId(String id);

    List<Profile> findByAreaCode(String areaCode);
}
