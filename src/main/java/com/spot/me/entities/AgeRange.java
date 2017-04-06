package com.spot.me.entities;

import javax.persistence.*;

@Entity
@Table(name="ageRanges")
public class AgeRange {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable=false)
    String range;

    public AgeRange(String range) {
        this.range = range;
    }

    public AgeRange() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
