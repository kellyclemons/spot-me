package com.spot.me.services;

import com.spot.me.entities.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by BHarris on 4/5/17.
 */
public interface MessageRepository extends CrudRepository<Message, String> {

    List<Message> findAllMessageByReceiverId(String id);
}
