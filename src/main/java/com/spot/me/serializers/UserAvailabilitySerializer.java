package com.spot.me.serializers;

import com.spot.me.entities.HasId;
import com.spot.me.entities.User;
import com.spot.me.entities.UserAvailability;

import java.util.HashMap;
import java.util.Map;



public class UserAvailabilitySerializer extends JsonDataSerializer {

    public String getType() {
        return "userAvailability";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        UserAvailability user = (UserAvailability) entity;

        result.put("days", user.getDays());

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}