package com.spot.me.serializers;

import com.spot.me.entities.Friend;
import com.spot.me.entities.HasId;

import java.util.HashMap;
import java.util.Map;

public class FriendSerializer extends JsonDataSerializer {
    public String getType() {
        return "friends";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        Friend friend = (Friend) entity;

        result.put("id", friend.getId());
        result.put("requester", friend.getRequester());
        result.put("requestee", friend.getRequestee());
        result.put("status", friend.getStatus());

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}
