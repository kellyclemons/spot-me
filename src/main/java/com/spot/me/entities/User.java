package com.spot.me.entities;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String password;

    @Column
    private String phoneNumber;

    @Column
    private String areaCode;

    @Column(length = 500)
    private String bio;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @ManyToMany(mappedBy = "users", targetEntity=userActivity.class)
    private Collection<userActivity> activities;

    public User() {
    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Collection<userActivity> getActivities() {
        return activities;
    }

    public void setActivities(Collection<userActivity> activities) {
        this.activities = activities;
    }
}
