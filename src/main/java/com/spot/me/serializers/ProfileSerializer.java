package com.spot.me.serializers;

import com.spot.me.entities.HasId;
import com.spot.me.entities.Profile;
import com.spot.me.entities.User;

import javax.persistence.Column;
import java.util.HashMap;
import java.util.Map;



public class ProfileSerializer extends JsonDataSerializer {

    public String getType() {
        return "users";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        Profile profile = (Profile) entity;

        result.put("id", profile.getId());
        result.put("phoneNumber", profile.getPhoneNumber());
        result.put("areaCode", profile.getAreaCode());
        result.put("bio", profile.getBio());
        result.put("latitude", profile.getLatitude());
        result.put("longitude", profile.getLongitude());

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}