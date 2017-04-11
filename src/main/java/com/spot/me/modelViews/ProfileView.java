package com.spot.me.modelViews;

import com.spot.me.entities.HasId;
import com.spot.me.entities.UserActivity;
import com.spot.me.entities.UserAvailability;

import javax.persistence.Column;
import java.util.List;

/**
 * Created by BHarris on 4/10/17.
 */
public class ProfileView implements HasId{
    String id;
    private String phoneNumber;
    private String areaCode;
    private String bio;
    private double latitude;
    private double longitude;
    private List<UserActivity> activities;
    private List<UserAvailability> availability;

    public ProfileView(String id, String phoneNumber, String areaCode, String bio, double latitude, double longitude, List<UserActivity> activities, List<UserAvailability> availability) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.areaCode = areaCode;
        this.bio = bio;
        this.latitude = latitude;
        this.longitude = longitude;
        this.activities = activities;
        this.availability = availability;
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

    public List<UserActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<UserActivity> activities) {
        this.activities = activities;
    }

    public List<UserAvailability> getAvailability() {
        return availability;
    }

    public void setAvailability(List<UserAvailability> availability) {
        this.availability = availability;
    }
}
