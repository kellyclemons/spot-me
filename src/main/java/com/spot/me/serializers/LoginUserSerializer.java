package com.spot.me.serializers;

import com.spot.me.entities.HasId;
import com.spot.me.entities.User;
import com.spot.me.utilities.LoginUser;

import java.util.HashMap;
import java.util.Map;

public class LoginUserSerializer extends JsonDataSerializer {

    public String getType() {
        return "users";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        LoginUser user = (LoginUser) entity;

        result.put("email", user.getEmail());
        result.put("password", user.getPassword());

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}

