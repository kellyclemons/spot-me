package com.spot.me.services;

import com.spot.me.entities.Friend;
import org.springframework.data.repository.CrudRepository;

public interface FriendRepository extends CrudRepository<Friend, String> {
}
