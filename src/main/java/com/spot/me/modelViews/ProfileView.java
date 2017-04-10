package com.spot.me.modelViews;

import javax.persistence.Column;

/**
 * Created by BHarris on 4/10/17.
 */
public class ProfileView {
    String id;
    private String phoneNumber;
    private String areaCode;
    private String bio;
    private double latitude;
    private double longitude;

    public ProfileView(String id, String phoneNumber, String areaCode, String bio, double latitude, double longitude) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.areaCode = areaCode;
        this.bio = bio;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
