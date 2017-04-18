package com.spot.me.utilities.Location;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by BHarris on 4/13/17.
 */
public class Geolocation {
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lng")
    private String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
