package com.spot.me.serializers;
import com.spot.me.entities.HasId;
import com.spot.me.entities.Profile;
import com.spot.me.entities.Rating;
import com.spot.me.entities.User;
import com.spot.me.modelViews.AverageRating;
import com.spot.me.modelViews.ProfileView;

import javax.persistence.Column;
import java.util.HashMap;
import java.util.Map;



public class AverageRatingSerializer extends JsonDataSerializer {

    public String getType() {
        return "profile";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        AverageRating rating = (AverageRating) entity;
        result.put("average-rating", rating.getRating());
        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}