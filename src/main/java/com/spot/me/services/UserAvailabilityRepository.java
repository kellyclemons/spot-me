package com.spot.me.services;

import com.spot.me.entities.UserAvailability;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserAvailabilityRepository extends CrudRepository<UserAvailability, String> {
    List<UserAvailability> findDayByUserId(String id);

    @Modifying
    @Transactional
    @Query("delete from UserAvailability u where user_id = ?1")
    void removeUserAvailabilitiesById(String id);
}
