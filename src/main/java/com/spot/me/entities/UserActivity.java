package com.spot.me.entities;
import javax.persistence.*;

import java.util.Collection;

@Entity
@Table(name="userActivities")
public class UserActivity {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private ActivityName activityName;

    public UserActivity() {
    }

    public UserActivity(User user, ActivityName activityName){
        this.user = user;
        this.activityName = activityName;
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

    public ActivityName getActivityName() {
        return activityName;
    }

    public void setActivityName(ActivityName activityName) {
        this.activityName = activityName;
    }
}
