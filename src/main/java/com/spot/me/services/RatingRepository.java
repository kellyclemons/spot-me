package com.spot.me.services;

import com.spot.me.entities.Message;
import com.spot.me.entities.Rating;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by abbh62 on 4/16/2017.
 */
public interface RatingRepository extends CrudRepository<Rating, String> {
    List<Rating> findAllRatingByReceiverId(String id);
}
