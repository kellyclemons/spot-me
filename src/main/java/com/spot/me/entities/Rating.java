package com.spot.me.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Entity
@Table(name="ratings")
public class Rating implements HasId {
    static final long serialVersionUID = 42L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name="rating")
    private int rating;

    @ManyToOne
    private User rater;

    @ManyToOne
    private User receiver;

    @Transient
    @JsonProperty("rater-id")
    private String rateId;

    @Transient
    @JsonProperty("receiver-id")
    private String recId;

    public Rating() {
    }

    public Rating(int rating, User rater, User receiver) {
        this.rating = rating;
        this.rater = rater;
        this.receiver = receiver;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getRater() {
        return rater;
    }

    public void setRater(User rater) {
        this.rater = rater;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getRateId() {
        return rateId;
    }

    public void setRateId(String rateId) {
        this.rateId = rateId;
    }

    public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId;
    }
}
