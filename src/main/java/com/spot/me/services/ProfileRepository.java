package com.spot.me.services;

import com.spot.me.entities.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.lang.annotation.Native;
import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, String> {
    Profile findFirstByUserId(String id);

    List<Profile> findByZipCode(String zipCode);

}
