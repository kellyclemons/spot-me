package com.spot.me.controllers;

import com.spot.me.Parers.RootParser;
import com.spot.me.entities.*;
import com.spot.me.serializers.LoginUserSerializer;
import com.spot.me.serializers.RootSerializer;
import com.spot.me.serializers.UserSerializer;
import com.spot.me.services.*;
import com.spot.me.utilities.JsonUser;
import com.spot.me.utilities.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

@CrossOrigin
@RestController
public class UserController {
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

    RootSerializer rootSerializer;
    UserSerializer userSerializer;
    LoginUserSerializer loginUserSerializer;

    public UserController() {
        rootSerializer = new RootSerializer();
        userSerializer = new UserSerializer();
        loginUserSerializer = new LoginUserSerializer();
    }

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
    public Map<String, Object> login(HttpServletResponse response, @RequestBody RootParser<User> parser) throws Exception {
        User user = parser.getData().getEntity();
        User existingUser = users.findFirstByEmail(user.getEmail());

//        if(existingUser == null || ! existingUser.verifyPassword(user.getPassword())) {
//            response.sendError(401, "Invalid Credentials");
//        }
        if(existingUser == null || ! existingUser.getPassword().equals(user.getPassword())) {
            response.sendError(401, "Invalid Credentials");
        }

        return rootSerializer.serializeOne(
                "/login/" + existingUser.getId(),
                existingUser,
                userSerializer);
    }

    @RequestMapping(path="/register", method=RequestMethod.POST)
    public Map<String, Object> register(HttpServletResponse response, @RequestBody RootParser<User> parser) throws Exception {
        User user = parser.getData().getEntity();
        User existingUser = users.findFirstByEmail(user.getEmail());

        if(existingUser != null) {
            response.sendError(422, "Username is taken.");
        }else{
            users.save(user);
        }
        return rootSerializer.serializeOne(
                "/register/" + user.getId(),
                user,
                userSerializer);
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

//    @RequestMapping(path="/add-zip", method=RequestMethod.POST)
//    public String addZip(@RequestBody Map<String, Object> body, HttpServletResponse response){
//        User user = users.findFirstByEmail((String)body.get("email"));
//        user.setAreaCode((String)body.get("zipcode"));
//        users.save(user);
//        return "";
//    }

    @RequestMapping(path="/edit-profile")
    public String updateProfile(@RequestBody Map<String, Object> body, HttpServletResponse response){

        return "";
    }

}