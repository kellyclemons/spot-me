package com.spot.me.controllers;

import com.spot.me.entities.Friend;
import com.spot.me.entities.User;
import com.spot.me.parsers.RootParser;
import com.spot.me.serializers.FriendSerializer;
import com.spot.me.serializers.RatingSerializer;
import com.spot.me.serializers.RootSerializer;
import com.spot.me.services.FriendRepository;
import com.spot.me.services.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class FriendController {

    @Autowired
    UserRepository users;

    @Autowired
    FriendRepository friends;

    RootSerializer rootSerializer;
    RatingSerializer ratingSerializer;
    FriendSerializer friendSerializer;

    public FriendController() {
        rootSerializer = new RootSerializer();
        ratingSerializer = new RatingSerializer();
        friendSerializer = new FriendSerializer();
    }

    @RequestMapping(path = "/friends", method = RequestMethod.POST)
    public Map<String, Object> requestFriend(HttpServletResponse response, @RequestBody RootParser<Friend> parser) {
        Friend data = parser.getData().getEntity();
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User req = users.findFirstByEmail(u.getName());
        User user = users.findFirstById(data.getRequesteridNum());
        Friend friend = new Friend(0, req, user);
        friends.save(friend);

        return rootSerializer.serializeOne(
                "/messages/" + friend.getId(),
                friend,
                friendSerializer
        );
    }

    @RequestMapping(path = "/friends/requests/{id}", method = RequestMethod.GET)
    public Map<String, Object> getAllFriendRequests(@PathVariable("userid") String userId) {
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByEmail(u.getName());
        List<Friend> friendRequests = friends.findAllByRequesteeIdAndStatus(user.getId(), 0);

        return rootSerializer.serializeMany(
                "/messages/" + user.getId(),
                friendRequests,
                friendSerializer);
    }

    @RequestMapping(path = "/friends", method=RequestMethod.PATCH)
    public Map<String, Object> updateProfile(HttpServletResponse response, @RequestBody RootParser<Friend> parser) {
        Friend data = parser.getData().getEntity();
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByEmail(u.getName());
        Friend request = friends.findOne(data.getId());

        if(data.getStatus() == 1) {
            request.setStatus(data.getStatus());
        }else if (data.getStatus() == 2) {
            request.setStatus(data.getStatus());
        }
        friends.save(request);

        return rootSerializer.serializeOne(
                "/messages/" + request.getId(),
                request,
                friendSerializer);
    }

}