package com.spot.me.services;

import com.spot.me.entities.UsersActivity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by BHarris on 4/5/17.
 */
public interface UserActivityRepository extends CrudRepository<UsersActivity, String> {
    List<UsersActivity> findAllByUserId(String id);

    @Modifying
    @Transactional
    @Query("delete from UsersActivity u where u.user.id = ?1")
    void removeUserActivitiesById(String id);

}
