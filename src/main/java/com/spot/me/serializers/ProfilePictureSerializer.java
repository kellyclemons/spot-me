package com.spot.me.serializers;


import com.spot.me.entities.HasId;
import com.spot.me.entities.ProfilePicture;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BHarris on 4/18/17.
 */
public class ProfilePictureSerializer extends JsonDataSerializer{
    public String getType() {
        return "profile-picture";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        ProfilePicture picture = (ProfilePicture) entity;

        result.put("photo-url", picture.getPhotoUrl());
        result.put("user-id", picture.getUser().getId());

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}
