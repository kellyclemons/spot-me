package com.spot.me.entities;
import javax.persistence.*;


@Entity
public class UserAvailability {
    static private final String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    @Id
    @GeneratedValue
    int id;

    @Column(nullable=false)
    String day;

    @ManyToOne
    private User user;

    public UserAvailability(User user, String day) {
        for(String d : days){
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

}
