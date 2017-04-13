package com.spot.me.serializers;

import com.spot.me.entities.HasId;
import com.spot.me.entities.Profile;
import com.spot.me.entities.User;
import com.spot.me.modelViews.ProfileView;

import javax.persistence.Column;
import java.util.HashMap;
import java.util.Map;



public class ProfileSerializer extends JsonDataSerializer {

    public String getType() {
        return "profile";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        ProfileView profile = (ProfileView) entity;
        result.put("name", profile.getName());
        result.put("email", profile.getEmail());
        result.put("id", profile.getId());
        result.put("phoneNumber", profile.getPhoneNumber());
        result.put("zipCode", profile.getZipCode());
        result.put("bio", profile.getBio());
        result.put("latitude", profile.getLatitude());
        result.put("longitude", profile.getLongitude());
        result.put("activites", profile.getActivities());
        result.put("availability", profile.getAvailability());
        result.put("ageRange", profile.getAgeRange());
        result.put("gender", profile.getGender());

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}