package com.spot.me.controllers;

import com.spot.me.Parsers.RootParser;
import com.spot.me.entities.*;
import com.spot.me.modelViews.ProfileView;
import com.spot.me.serializers.*;
import com.spot.me.serializers.AreaCodeSerializer;
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
    AreaCodeSerializer areaCodeSerializer;


    public UserController() {
        rootSerializer = new RootSerializer();
        userSerializer = new UserSerializer();
        activityNameSerializer = new ActivityNameSerializer();
        userAvailabilitySerializer = new UserAvailabilitySerializer();
        profileSerializer = new ProfileSerializer();
        areaCodeSerializer = new AreaCodeSerializer();
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
                areaCodeSerializer);
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
            aDays.add(x.getDay());
        }

        List<UserActivity> favoriteActivities = userActivity.findAllByUserId(id);
        List<String> activites = new ArrayList<>();
        for (UserActivity x : favoriteActivities){
            activites.add(x.getActivityName().getActivityName());
        }
        ProfileView p = new ProfileView(id, profile.getPhoneNumber(),profile.getAreaCode(),profile.getBio(),profile.getLatitude(),profile.getLongitude(),activites, aDays);
        return rootSerializer.serializeOne(
                "/profile/" + p.getId(),
                p,
                profileSerializer);
    }

    @RequestMapping(path="/users/{areaCode}")
    public Map<String, Object> findAllProfileInAreaCode(@PathVariable("areaCode") String areaCode){
        List<ProfileView> allUsers = new ArrayList<>();
        Iterable<Profile> usersInArea =  profiles.findByAreaCode(areaCode);

        for(Profile p : usersInArea) {
            allUsers.add(createProfile(p));
        }


        return rootSerializer.serializeMany("/profile/", allUsers, profileSerializer);
    }

    @RequestMapping(path="/users/{areaCode}/{query}")
    public Map<String, Object> findAllProfileInAreaCode(@PathVariable("areaCode") String areaCode, @PathVariable("query") List<String> query) {
        List<ProfileView> allUsers = new ArrayList<>();
        List<Profile> usersInArea = filterData(query);


        for(Profile p : usersInArea) {
            allUsers.add(createProfile(p));
        }

        return rootSerializer.serializeMany("/profile/", allUsers, profileSerializer);

    }

    public ProfileView createProfile(Profile p ){

            String userId = p.getUser().getId();
            List<UserAvailability> availabilityDays = userAvailability.findDayByUserId(p.getUser().getId());
            List<String> aDays = new ArrayList<>();
            for (UserAvailability x : availabilityDays){
                aDays.add(x.getDay());
            }

            List<UserActivity> favoriteActivities = userActivity.findAllByUserId(p.getUser().getId());
            List<String> activites = new ArrayList<>();
            for (UserActivity x : favoriteActivities){
                activites.add(x.getActivityName().getActivityName());
            }
            ProfileView profile = new ProfileView(userId, p.getPhoneNumber(),p.getAreaCode(),p.getBio(),p.getLatitude(),p.getLongitude(),activites, aDays);
            return profile;

    }

    public List<Profile> filterData(List<String> query){
        List<Profile> filteredProfiles = new ArrayList<>();
        switch(query.size()){
            case 1: filteredProfiles.add(profiles.findByAreaCodeAndOneFilter(query.get(0)));
                break;
            case 2: filteredProfiles.add(profiles.findByAreaCodeAndTwoFilter(query.get(0), query.get(1)));
                break;
            case 3: filteredProfiles.add(profiles.findByAreaCodeAndThreeFilter(query.get(0), query.get(1), query.get(2)));
                break;
            case 4: filteredProfiles.add(profiles.findByAreaCodeAndFourFilter(query.get(0), query.get(1), query.get(2), query.get(3)));
                break;
            default:
                break;
        }
        return filteredProfiles;
    }

}