package com.spot.me.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="userAgeRange")
public class UserAgeRange {
    static final long serialVersionUID = 42L;
    static private final String[] range = {"18-24", "25-34", "35-44", "45-54", "55-64", "over 65", "N/A"};

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @JsonProperty("age-range")
    private String ageRange;

    @ManyToOne
    private User user;

    public UserAgeRange() {
    }

    public UserAgeRange(User user, String ageRange){
        for (String d : range) {
            if (d.equals(ageRange)) {
                this.user = user;
                this.ageRange = ageRange;
            }
        }

    }

    public static String[] getRange() {
        return range;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
