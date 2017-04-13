package com.spot.me.modelViews;

import com.spot.me.entities.HasId;

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
    private String zipCode;
    private String bio;
    private double latitude;
    private double longitude;
    private String ageRange;
    private String gender;
    private List<String> activities;
    private List<String> availability;


    public ProfileView(String id, String name, String email, String phoneNumber, String zipCode, String bio, double latitude, double longitude, String ageRange, String gender, List<String> activities, List<String> availability) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.bio = bio;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ageRange = ageRange;
        this.activities = activities;
        this.availability = availability;
        this.gender = gender;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
