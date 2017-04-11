package com.spot.me.serializers;

import com.spot.me.entities.HasId;
import com.spot.me.entities.Profile;
import com.spot.me.entities.User;

import java.util.HashMap;
import java.util.Map;



public class AreaCodeSerializer extends JsonDataSerializer {

    public String getType() {
        return "users";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        Profile profile = (Profile) entity;

        result.put("id", profile.getId());
        result.put("areaCode", profile.getAreaCode());

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}