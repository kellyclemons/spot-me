package com.spot.me.entities;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Entity
public class UserAvailability implements HasId{
    static final long serialVersionUID = 42L;
    static private final String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    String id;

    @Column(nullable=false)
    String day;

    @ManyToOne
    private User user;

    @Transient
    private List<String> days;

    public UserAvailability(User user, String day) {
        for(String d : daysOfWeek){
            if(d.equals(day)) {
                this.user = user;
                this.day = day;
            }
        }
    }

    public UserAvailability() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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


    public void setDays(List<String> days) {
        this.days = days;
    }

    public List<String> getDays() {
        return days;
    }

}

