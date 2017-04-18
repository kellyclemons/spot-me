package com.spot.me.modelViews;

import com.spot.me.entities.HasId;

/**
 * Created by abbh62 on 4/16/2017.
 */
public class AverageRating implements HasId {
    String id;

    double rating;

    public AverageRating(String id, double rating) {
        this.id = id;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
