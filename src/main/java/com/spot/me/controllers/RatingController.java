package com.spot.me.controllers;


import com.spot.me.parsers.RootParser;
import com.spot.me.entities.Rating;
import com.spot.me.entities.User;
import com.spot.me.modelViews.AverageRating;
import com.spot.me.serializers.AverageRatingSerializer;
import com.spot.me.serializers.RatingSerializer;
import com.spot.me.serializers.RootSerializer;
import com.spot.me.services.RatingRepository;
import com.spot.me.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class RatingController {
    @Autowired
    UserRepository users;

    @Autowired
    RatingRepository ratings;

    RootSerializer rootSerializer;
    RatingSerializer ratingSerializer;
    AverageRatingSerializer averageRatingSerializer;

    public RatingController() {
        rootSerializer = new RootSerializer();
        ratingSerializer = new RatingSerializer();
        averageRatingSerializer = new AverageRatingSerializer();
    }

    @RequestMapping(value = "/rating", method = RequestMethod.POST)
    public Map<String, Object> SendMessage(HttpServletResponse response, @RequestBody RootParser<Rating> parser) {
        Rating rate = parser.getData().getEntity();
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User rater = users.findFirstByEmail(u.getName());

        User receiver = users.findFirstById(rate.getRecId());
        Rating rating = new Rating(rate.getRating(), rater, receiver);
        ratings.save(rating);

        return rootSerializer.serializeOne(
                "/rating/" + rater.getId(),
                rating,
                ratingSerializer);
    }

    @RequestMapping(path = "/users/{userid}/rating", method= RequestMethod.GET)
    public Map<String, Object> returnAverageRating(@PathVariable("userid") String userId) {
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByEmail(u.getName());

        List<Rating> rating = ratings.findAllRatingByReceiverId(userId);
        int num = 0;
        for (Rating r : rating) {
            num += r.getRating();
        }

        double averageRating = num / rating.size();
        AverageRating ar = new AverageRating(user.getId(), averageRating);

        return rootSerializer.serializeOne(
                "/rating/" + user.getId(),
                ar,
                averageRatingSerializer
                );
    }

}
