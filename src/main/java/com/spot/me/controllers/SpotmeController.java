package com.spot.me.controllers;

import com.spot.me.entities.*;
import com.spot.me.services.*;
import com.spot.me.utilities.GetEmailAndActivity;
import com.spot.me.utilities.JsonUser;
import jodd.json.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class SpotmeController {
    @Autowired
    UserRepository users;
    @Autowired
    private ActivityNameRepository activityName;
    @Autowired
    private UserActivityRepository userActivity;
    @Autowired
    private UserAvailabilityRepository userAvailability;
    @Autowired
    private UserAgeRangeRepository userAgeRange;

    @PostConstruct
    public void init() {
        if(activityName.count() == 0) {
            String[] activities = {
                    "Tennis",
                    "Running",
                    "Lifting",
                    "Walking"
            };

            for (String a : activities) {
                activityName.save(new ActivityName(a));
            }
        }
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
    public User addActivity(@RequestBody Map<String, Object> body, HttpServletResponse response){

        User user = users.findFirstByEmail((String)body.get("email"));
        ArrayList list = (ArrayList) body.get("activity");

        for(Object a : list) {
            ActivityName name = activityName.findFirstByName((String)a);
            UserActivity u = new UserActivity(user,name);
            userActivity.save(u);
        }

        return user;
    }

    @RequestMapping(path="/add-availability", method=RequestMethod.POST)
    public String addAvailability(@RequestBody Map<String, Object> body, HttpServletResponse response){
        User user = users.findFirstByEmail((String)body.get("email"));
        ArrayList list = (ArrayList) body.get("availability");
        for(Object s : list) {
            userAvailability.save(new UserAvailability(user,(String)s));
        }
        return "";
    }

    @RequestMapping(path="/add-zip", method=RequestMethod.POST)
    public String addZip(@RequestBody Map<String, Object> body, HttpServletResponse response){
        User user = users.findFirstByEmail((String)body.get("email"));
        user.setAreaCode((String)body.get("zipcode"));
        users.save(user);
        return "";
    }

    @RequestMapping(path="/edit-profile")
    public String updateProfile(@RequestBody Map<String, Object> body, HttpServletResponse response){

        return "";
    }

}