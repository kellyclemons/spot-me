package com.spot.me.controllers;

import com.spot.me.parsers.RootParser;
import com.spot.me.entities.*;
import com.spot.me.modelViews.ProfileView;
import com.spot.me.serializers.*;
import com.spot.me.serializers.ZipCodeSerializer;
import com.spot.me.services.*;
import com.spot.me.utilities.Location.Geocode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    RootSerializer rootSerializer;
    UserSerializer userSerializer;
    ActivityNameSerializer activityNameSerializer;
    UserAvailabilitySerializer userAvailabilitySerializer;
    ProfileSerializer profileSerializer;
    ZipCodeSerializer zipCodeSerializer;


    public UserController() {
        rootSerializer = new RootSerializer();
        userSerializer = new UserSerializer();
        activityNameSerializer = new ActivityNameSerializer();
        userAvailabilitySerializer = new UserAvailabilitySerializer();
        profileSerializer = new ProfileSerializer();
        zipCodeSerializer = new ZipCodeSerializer();
    }

    @PostConstruct
    public void init() {
        if (activityName.count() == 0) {
            String[] activities = {
                    "Running",
                    "Hiking",
                    "Cycling",
                    "Swimming",
                    "Tennis",
                    "Yoga",
                    "Lifting",
                    "Golf",
                    "Boxing"
            };

            for (String a : activities) {
                activityName.save(new ActivityName(a));
            }
        }
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public Map<String, Object> register(HttpServletResponse response, @RequestBody RootParser<User> parser) throws Exception {
        User user = parser.getData().getEntity();
        User existingUser = users.findFirstByEmail(user.getEmail());
        User u = new User();
        if (existingUser != null) {
            response.sendError(422, "Username is taken.");
        } else {
            u = new User(user.getEmail(), user.getName(), bCryptPasswordEncoder.encode(user.getPassword()));
            users.save(u);
        }
        return rootSerializer.serializeOne(
                "/register/" + u.getId(),
                u,
                userSerializer);
    }

    @RequestMapping(path="/users/current", method= RequestMethod.GET)
    public Map<String, Object> currentUser(){
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByEmail(u.getName());

        return rootSerializer.serializeOne(
                "/users/" + user.getId(),
                user,
                userSerializer);
    }

    @RequestMapping(path = "/users/{id}/profile", method = RequestMethod.PATCH)
    public Map<String, Object> updateProfile(HttpServletResponse response, @RequestBody RootParser<Profile> parser) throws Exception {
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByEmail(u.getName());
        if(user == null) {
            response.sendError(404, "user could not be found.");
        }
        Profile profile = parser.getData().getEntity();
        if (profiles.findFirstByUserId(user.getId()) == null) {
            Profile p = new Profile(user);
            profiles.save(p);
        }
        Profile p = profiles.findFirstByUserId(user.getId());

        if (profile.getZipCode() != null) {
            p.setZipCode(profile.getZipCode());
            String location = getLocationFromZip(profile.getZipCode());
            String[] loc = location.split(",");

            for(int i=0; i< loc.length; i++) {
                Double num = Double.parseDouble(loc[i]);
                if(i == 0) {
                    p.setZipLatitude(num);
                } else {
                    p.setZipLongitude(num);
                }
            }
        }

        if (profile.getBio() != null) {
            p.setBio(profile.getBio());
        }
        if (profile.getPhoneNumber() != null) {
            p.setPhoneNumber(profile.getPhoneNumber());
        }
        if (profile.getGender() != null) {
            p.setGender(profile.getGender());
        }


        if (profile.getActivityNames() != null) {
            userActivity.removeUserActivitiesById(user.getId());
            for (String a : profile.getActivityNames()) {
                ActivityName name = activityName.findFirstByActivityName(a);
                userActivity.save(new UsersActivity(user, name));
            }
        }

        if (profile.getDaysAvailable() != null) {
            userAvailability.removeUserAvailabilitiesById(user.getId());
            for (String a : profile.getDaysAvailable()) {
                userAvailability.save(new UserAvailability(user, a));
            }
        }
        if (profile.getLatitude() != 0) {
            p.setLatitude(profile.getLatitude());
        }

        if (profile.getLongitude() != 0) {
            p.setLongitude(profile.getLongitude());
        }

        if (profile.getAgeRange() != null) {
            userAgeRange.removeUserAgeRangeByUserId(user.getId());
            userAgeRange.save(new UserAgeRange(user, profile.getAgeRange()));
        }

        profiles.save(p);

        ProfileView profileView = createProfile(p);
        return rootSerializer.serializeOne(
                "/profile/" + profile.getId(),
                profileView,
                profileSerializer);
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public Map<String, Object> findOneProfile(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByEmail(u.getName());
        if(user == null) {
            response.sendError(404, "user could not be found.");
        }
        Profile profile = profiles.findFirstByUserId(user.getId());
        ProfileView pv = createProfile(profile);
        return rootSerializer.serializeOne(
                "/profile/" + pv.getId(),
                pv,
                profileSerializer);
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public Map<String, Object> findAllProfileInZipCodeWithFilter(@RequestParam(value = "filter[zip]", required = false) String zipCode, @RequestParam(value = "filter[activity]", required = false) List<String> filter) {
        List<ProfileView> usersWithInterest = new ArrayList<>();
        if (zipCode.equals("")) {
            zipCode = "37243";
        }
        List<Profile> usersInArea = profiles.findByZipCode(zipCode);
        if (filter == null || filter.size() <= 0) {
            for (Profile p : usersInArea) {
                usersWithInterest.add(createProfile(p));
            }
        } else {
            for (Profile p : usersInArea) {
                ProfileView profile = createProfile(p);
                if (profile.getActivities().containsAll(filter)) {
                    usersWithInterest.add(profile);
                }
            }
        }

        return rootSerializer.serializeMany("/profile/", usersWithInterest, profileSerializer);
    }

    public ProfileView createProfile(Profile p) {

        String userId = p.getUser().getId();
        User user = users.findFirstById(userId);

        List<UserAvailability> availabilityDays = userAvailability.findDayByUserId(p.getUser().getId());
        List<String> aDays = new ArrayList<>();
        for (UserAvailability x : availabilityDays) {
            aDays.add(x.getDay());
        }

        List<UsersActivity> favoriteActivities = userActivity.findAllByUserId(p.getUser().getId());
        List<String> activities = new ArrayList<>();
        for (UsersActivity x : favoriteActivities) {
            activities.add(x.getActivityName().getActivityName());
        }

        UserAgeRange ageRange = userAgeRange.findFirstByUserId(userId);
        if(ageRange == null) {
            userAgeRange.save(new UserAgeRange(user, "N/A"));
        }
        ageRange = userAgeRange.findFirstByUserId(userId);
        ProfileView profile = new ProfileView(userId, user.getName(), user.getEmail(), p.getPhoneNumber(),
                p.getZipCode(), p.getBio(), p.getLatitude(), p.getLongitude(), ageRange.getAgeRange(), p.getGender(),
                p.getZipLatitude(), p.getZipLongitude(), activities, aDays);
        return profile;
    }

    public String getLocationFromZip(String zip) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url="https://maps.googleapis.com/maps/api/geocode/json?address=" + zip + " +&key=AIzaSyDoKoDr3vBROrz5WuUfyak1S8CdCh08F1w";

        RestTemplate rt = new RestTemplate();

        ResponseEntity<Geocode> geocode = rt.exchange(url, HttpMethod.GET, entity, Geocode.class);
        String lat = geocode.getBody().getLat();
        String lng = geocode.getBody().getLng();
        String coordinates = lat + "," + lng;
        return coordinates;
    }

}