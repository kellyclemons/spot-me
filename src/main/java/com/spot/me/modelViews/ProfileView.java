package com.spot.me.modelViews;

import com.spot.me.entities.ActivityName;
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

    //new
    private String name;
    private String email;

    private String phoneNumber;
    private String areaCode;
    private String bio;
    private double latitude;
    private double longitude;
    private List<String> activities;
    private List<String> availability;

    public ProfileView(String id, String phoneNumber, String areaCode, String bio, double latitude, double longitude, List<String> activities, List<String> availability, String name, String email) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.areaCode = areaCode;
        this.bio = bio;
        this.latitude = latitude;
        this.longitude = longitude;
        this.activities = activities;
        this.availability = availability;

        this.name = name;
        this.email = email;
    }

    public ProfileView() {
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

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public List<String> getAvailability() {
        return availability;
    }

    public void setAvailability(List<String> availability) {
        this.availability = availability;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
