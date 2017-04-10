package com.spot.me.serializers;

import com.spot.me.entities.ActivityName;
import com.spot.me.entities.HasId;
import com.spot.me.entities.User;

import java.util.HashMap;
import java.util.Map;



public class ActivityNameSerializer extends JsonDataSerializer {

    public String getType() {
        return "activities";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        ActivityName activityName = (ActivityName) entity;

        result.put("id", activityName.getId());
        result.put("name", activityName.getName());


        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}