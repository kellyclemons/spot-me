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
    private String raterId;

    @Transient
    @JsonProperty("receiver-id")
    private String receiverId;

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

    public String getRaterId() {
        return raterId;
    }

    public void setRaterId(String raterId) {
        this.raterId = raterId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
}
