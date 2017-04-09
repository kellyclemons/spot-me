package com.spot.me.services;

import com.spot.me.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by BHarris on 4/5/17.
 */
public interface UserRepository extends CrudRepository<User, String> {
    User findFirstByEmail(String email);
}
