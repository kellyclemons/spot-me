package com.spot.me.entities;
import javax.persistence.*;


@Entity
public class UserAvailability {
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private AvailabilityDay availableDay;

    public UserAvailability() {
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

    public AvailabilityDay getAvailableDay() {
        return availableDay;
    }

    public void setAvailableDay(AvailabilityDay availableDay) {
        this.availableDay = availableDay;
    }
}
