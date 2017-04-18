package com.spot.me.services;

import com.spot.me.entities.Friend;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendRepository extends CrudRepository<Friend, String> {

    List<Friend> findAllByRequesteeIdAndStatus(String id, int status);


}
