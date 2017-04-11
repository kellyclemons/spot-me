package com.spot.me.controllers;

import com.spot.me.Parsers.RootParser;
import com.spot.me.entities.*;
import com.spot.me.modelViews.ProfileView;
import com.spot.me.serializers.*;
import com.spot.me.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    private ProfileRepository profiles;

    RootSerializer rootSerializer;
    UserSerializer userSerializer;
    ActivityNameSerializer activityNameSerializer;
    UserAvailabilitySerializer userAvailabilitySerializer;
    ProfileSerializer profileSerializer;


    public UserController() {
        rootSerializer = new RootSerializer();
        userSerializer = new UserSerializer();
        activityNameSerializer = new ActivityNameSerializer();
        userAvailabilitySerializer = new UserAvailabilitySerializer();
        profileSerializer = new ProfileSerializer();
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

        if(existingUser == null || ! existingUser.verifyPassword(user.getPassword())) {
            response.sendError(401, "Invalid Credentials");
        }

        return rootSerializer.serializeOne(
                "/login/" + existingUser.getId(),
                existingUser,
                userSerializer);
    }

    @RequestMapping(path="/users", method=RequestMethod.POST)
    public Map<String, Object> register(HttpServletResponse response, @RequestBody RootParser<User> parser) throws Exception {
        User user = parser.getData().getEntity();
        User existingUser = users.findFirstByEmail(user.getEmail());
        User u = new User();
        if(existingUser != null) {
            response.sendError(422, "Username is taken.");
        }else{
            u = new User(user.getEmail(), user.getName(), user.createHashPassword(user.getPassword()));
            users.save(u);
        }
        return rootSerializer.serializeOne(
                "/register/" + u.getId(),
                u,
                userSerializer);
    }

    @RequestMapping(path="/activities", method=RequestMethod.POST)
    public Map<String, Object> addActivity(HttpServletResponse response, @RequestBody RootParser<ActivityName> parser){
        ActivityName activity = parser.getData().getEntity();
        User user = users.findFirstById(activity.getId());

        for (String a : activity.getName()) {
            ActivityName name = activityName.findFirstByActivityName(a);
            userActivity.save(new UserActivity(user, name));
        }

        return rootSerializer.serializeOne(
                "/activities/" + activity.getId(),
                activity,
                activityNameSerializer);
    }

    @RequestMapping(path="/availabilities", method=RequestMethod.POST)
    public Map<String, Object> addAvailability(HttpServletResponse response, @RequestBody RootParser<UserAvailability> parser){
        UserAvailability availability = parser.getData().getEntity();
        User user = users.findFirstById(availability.getId());

        // todo: later try to write this in a Functional way!
        for(String a : availability.getDays()) {
            userAvailability.save(new UserAvailability(user,a));
        }
        return rootSerializer.serializeOne(
                "/availabilities/" + availability.getId(),
                availability,
                userAvailabilitySerializer);
    }

    @RequestMapping(path="/areaCode", method=RequestMethod.POST)
    public Map<String, Object> addZip(HttpServletResponse response, @RequestBody RootParser<Profile> parser){
        Profile profile = parser.getData().getEntity();
        User user = users.findFirstById(profile.getId());

        profiles.save(new Profile(profile.getAreaCode(), user));

        return rootSerializer.serializeOne(
                "/areaCode/" + profile.getId(),
                profile,
                profileSerializer);
    }

    @RequestMapping(path="/profile", method = RequestMethod.POST)
    public Map<String, Object> updateProfile(HttpServletResponse response, @RequestBody RootParser<Profile> parser){
        Profile profile = parser.getData().getEntity();
        User user = users.findFirstById(profile.getId());

        profiles.save(new Profile(profile.getPhoneNumber(), profile.getAreaCode(), profile.getBio(), profile.getUser()));

        return rootSerializer.serializeOne(
                "/areaCode/" + profile.getId(),
                profile,
                profileSerializer);
    }

    @RequestMapping(path = "/profile/{id}", method = RequestMethod.GET)
    public Map<String, Object> findOneProfile(@PathVariable("id") String id) {

        Profile profile = profiles.findFirstByUserId(id);
        List<UserAvailability> availabilityDays = userAvailability.findDayByUserId(id);
        List<String> aDays = new ArrayList<>();
        for (UserAvailability x : availabilityDays){
            System.out.println(x);
            aDays.add(x.getDay());
        }
        System.out.println(aDays.toString());
        List<UserActivity> favoriteAcivities = userActivity.findAllByUserId(id);
        ProfileView p = new ProfileView(profile.getId(), profile.getPhoneNumber(),profile.getAreaCode(),profile.getBio(),profile.getLatitude(),profile.getLongitude(),favoriteAcivities, availabilityDays);
        return rootSerializer.serializeOne(
                "/profile/" + profile.getId(),
                p,
                profileSerializer);
    }

    @RequestMapping(path="/users/{areaCode}")
    public Map<String, Object> findAllProfileInAreaCode(@PathVariable("areaCode") String areaCode){
        Iterable<Profile> usersInArea =  profiles.findByAreaCode(areaCode);

        return rootSerializer.serializeMany("/profile/", usersInArea, profileSerializer);
    }

}