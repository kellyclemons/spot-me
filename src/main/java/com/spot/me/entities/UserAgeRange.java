package com.spot.me.entities;

import javax.persistence.*;

@Entity
@Table(name="userAgeRange")
public class UserAgeRange {
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private AgeRange ageRange;

    public UserAgeRange(User user, AgeRange ageRange) {
        this.user = user;
        this.ageRange = ageRange;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AgeRange getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(AgeRange ageRange) {
        this.ageRange = ageRange;
    }
}
