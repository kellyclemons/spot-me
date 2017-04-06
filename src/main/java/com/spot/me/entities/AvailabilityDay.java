package com.spot.me.entities;

import javax.persistence.*;

@Entity
@Table(name="availabilityDay")
public class AvailabilityDay {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String day;

    public AvailabilityDay() {
    }

    public AvailabilityDay(String day) {
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}
