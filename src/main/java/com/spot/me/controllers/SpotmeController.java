package com.spot.me.controllers;

import com.spot.me.entities.ActivityName;
import com.spot.me.entities.User;
import com.spot.me.entities.UserActivity;
import com.spot.me.services.ActivityNameRepository;
import com.spot.me.services.UserActivityRepository;
import com.spot.me.services.UserRepository;
import com.spot.me.utilities.GetEmailAndActivity;
import com.spot.me.utilities.JsonUser;
import jodd.json.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
public class SpotmeController {
    @Autowired
    UserRepository users;

    @Autowired
    ActivityNameRepository activityName;

    @Autowired
    UserActivityRepository userActivity;

    @PostConstruct
    public void init() {
//        String[] activities = {
//                "tennis",
//                "running",
//                "lifting",
//                "walking"
//        };
//
//        for(String a : activities) {
//            activityName.save(new ActivityName(a));
//        }
    }


    @RequestMapping(path="/login", method=RequestMethod.POST)
    public User login(@RequestBody String body, HttpServletResponse response) throws Exception {
        JsonParser p = new JsonParser();
        JsonUser name = p.parse(body, JsonUser.class);


        User user = users.findFirstByEmail(name.getEmail());
        if(user == null) {
            return user;
        }else if (! user.verifyPassword(name.getPassword())) {
            response.sendError(401, "Invalid Credentials");
        }
        return user;
    }

    @RequestMapping(path="/register", method=RequestMethod.POST)
    public User Register(@RequestBody String body,  HttpServletResponse response) throws Exception {
        JsonParser p = new JsonParser();
        JsonUser u = p.parse(body, JsonUser.class);

        User user = users.findFirstByEmail(u.getEmail());
        if(user != null) {
            response.sendError(422, "Username is taken.");
        }else{
            user = new User(u.getEmail(), u.getName(), u.getPassword());
            users.save(user);
        }

        return user;
    }

    @RequestMapping(path="/add-activity", method=RequestMethod.POST)
    public User addActivity(@RequestBody String body, HttpServletResponse response){
        JsonParser p = new JsonParser();
        GetEmailAndActivity activity = p.parse(body, GetEmailAndActivity.class);

        User user = users.findFirstByEmail(activity.getEmail());
        ActivityName a = activityName.findFirstByName(activity.getActivity());
        UserActivity u = new UserActivity(user, a);
        userActivity.save(u);
        return user;
    }


}
