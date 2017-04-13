package com.spot.me.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="profiles")
public class Profile implements HasId{
    static final long serialVersionUID = 42L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    String id;

    @Column
    private String phoneNumber;

    @Column
    private String zipCode;

    @Column(length = 500)
    private String bio;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Column
    private String gender;

    @OneToOne
    private User user;

    @Transient
    List<String> activityNames;

    @Transient
    List<String> daysAvailable;

    @Transient String ageRange;

    public Profile(String zipCode, User user) {
        this.zipCode = zipCode;
        this.user = user;
    }

    public Profile(String phoneNumber, String zipCode, String bio, User user) {
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.bio = bio;
        this.user = user;
    }

    public Profile() {
    }

    public Profile(User user) {
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getActivityNames() {
        return activityNames;
    }

    public void setActivityNames(List<String> activityNames) {
        this.activityNames = activityNames;
    }

    public List<String> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(List<String> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }
}
