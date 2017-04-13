package com.spot.me.services;

import com.spot.me.entities.UserAgeRange;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by BHarris on 4/6/17.
 */
public interface UserAgeRangeRepository extends CrudRepository<UserAgeRange, String> {
    UserAgeRange findFirstByUserId(String id);

    @Modifying
    @Transactional
    @Query("delete from UserAgeRange u where u.user.id = ?1")
    void removeUserAgeRangeByUserId(String id);
}
