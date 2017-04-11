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

//    @Query("select distinct user.id" +
//            "  from UserActivity" +
//            "  join Profile on UserActivity.userId=Profile.userId" +
//            "  join ActivityName on UserActivity.name.id=ActivityName.id" +
//            "  where ActivityName.activityName = ?1" +
//            "  and Profile.areaCode=?2")
//    List<String> findByAreaCodeAndOneFilter(String a, String b);


    List<User> findUserByName_NameAndUser_Profile_AreaCode(String name, String ac);
}
