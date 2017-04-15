package com.spot.me.utilities.Location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by BHarris on 4/14/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    Geometry geometry;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
