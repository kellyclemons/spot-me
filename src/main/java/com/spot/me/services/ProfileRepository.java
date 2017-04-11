package com.spot.me.services;

import com.spot.me.entities.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.lang.annotation.Native;
import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, String> {
    Profile findFirstByUserId(String id);

    List<Profile> findByAreaCode(String areaCode);


//    @Query("SELECT DISTINCT profiles.userId" +
//            "  FROM userActivities" +
//            "  Join profiles ON userActivities.userId=profiles.userId" +
//            "  JOIN activities ON userActivities.nameId=activities.id" +
//            "  WHERE activities.activityName in (?1, ?2)" +
//            "  AND profiles.areaCode=?3")
//    List<String> findByAreaCodeAndTwoFilter(String a, String b, String c);
//
//    @Query("SELECT DISTINCT profiles.userId" +
//            "  FROM userActivities" +
//            "  Join profiles ON userActivities.userId=profiles.userId" +
//            "  JOIN activities ON userActivities.nameId=activities.id" +
//            "  WHERE activities.activityName in (?1, ?2, ?3)" +
//            "  AND profiles.areaCode=?4")
//    List<String> findByAreaCodeAndThreeFilter(String a, String b, String c, String d);
//
//    @Query("SELECT DISTINCT profiles.userId" +
//            "  FROM userActivities" +
//            "  Join profiles ON userActivities.userId=profiles.userId" +
//            "  JOIN activities ON userActivities.nameId=activities.id" +
//            "  WHERE activities.activityName in (?1, ?2, ?3, ?4)" +
//            "  AND profiles.areaCode=?5")
//    List<String> findByAreaCodeAndFourFilter(String a, String b, String c, String d, String e);
}
