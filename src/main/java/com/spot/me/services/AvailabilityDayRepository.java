package com.spot.me.services;

import com.spot.me.entities.AvailabilityDay;
import org.springframework.data.repository.CrudRepository;


public interface AvailabilityDayRepository extends CrudRepository<AvailabilityDay, Integer> {
    AvailabilityDay findFirstByDay(String day);
}
