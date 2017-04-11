package com.spot.me.services;

import com.spot.me.entities.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by BHarris on 4/7/17.
 */
public interface ProfileRepository extends CrudRepository<Profile, String> {
    Profile findFirstByUserId(String id);

    List<Profile> findByAreaCode(String areaCode);

    @Query("Select Min(id) from AnonFile WHERE toSave = false")
    Profile findByAreaCodeAndOneFilter(String a);

    @Query("Select Min(id) from AnonFile WHERE toSave = false")
    Profile findByAreaCodeAndTwoFilter(String a, String b);

    @Query("Select Min(id) from AnonFile WHERE toSave = false")
    Profile findByAreaCodeAndThreeFilter(String a, String b, String c);

    @Query("Select Min(id) from AnonFile WHERE toSave = false")
    Profile findByAreaCodeAndFourFilter(String a, String b, String c, String d);
}
