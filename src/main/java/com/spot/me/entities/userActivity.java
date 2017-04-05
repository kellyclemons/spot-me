package com.spot.me.entities;
import javax.persistence.*;

import java.util.Collection;

@Entity
@Table(name="userActivities")
public class userActivity {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User users;

    @ManyToOne
    private ActivityName activityName;

    public userActivity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public ActivityName getActivityName() {
        return activityName;
    }

    public void setActivityName(ActivityName activityName) {
        this.activityName = activityName;
    }
}
