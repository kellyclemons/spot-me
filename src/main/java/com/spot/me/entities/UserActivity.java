package com.spot.me.entities;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.Collection;

@Entity
@Table(name="userActivities")
public class UserActivity {
    static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

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

    public ActivityName getActivityName() {
        return activityName;
    }

    public void setActivityName(ActivityName activityName) {
        this.activityName = activityName;
    }
}
