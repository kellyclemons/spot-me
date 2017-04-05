package com.spot.me.entities;
import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name="ActivityName")
public class ActivityName {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable=false)
    private String name;

    public ActivityName() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
