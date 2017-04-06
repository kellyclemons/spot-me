package com.spot.me.services;

import com.spot.me.entities.AgeRange;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by BHarris on 4/6/17.
 */
public interface AgeRangeRepository extends CrudRepository<AgeRange, Integer> {
}
