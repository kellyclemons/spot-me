package com.spot.me.services;

import com.spot.me.entities.ProfilePicture;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by BHarris on 4/18/17.
 */
public interface ProfilePictureRepository extends CrudRepository<ProfilePicture, String> {
    ProfilePicture findFirstByUserId(String id);
}
