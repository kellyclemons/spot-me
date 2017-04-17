package com.spot.me.controllers;

import com.spot.me.Parsers.RootParser;
import com.spot.me.entities.Friend;
import com.spot.me.serializers.FriendSerializer;
import com.spot.me.serializers.RatingSerializer;
import com.spot.me.serializers.RootSerializer;
import com.spot.me.services.FriendRepository;
import com.spot.me.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

        Friend friend = new Friend(0, data.getRequester(), data.getRequestee());
        friends.save(friend);

        return rootSerializer.serializeOne(
                "/messages/" + friend.getId(),
                friend,
                friendSerializer);
    }

//    @RequestMapping(path="/friends", method = RequestMethod.PATCH)
//    public Map<String, Object> changeFriendStatus(HttpServletResponse response, @RequestBody RootParser<Friend> parser) {
//
//    }
}