package com.spot.me.controllers;

import com.spot.me.entities.User;
import com.spot.me.services.UserRepository;
import com.spot.me.utilities.JsonUser;
import jodd.json.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@CrossOrigin
@RestController
public class SpotmeController {
    @Autowired
    UserRepository users;

    @RequestMapping(path="/")
    public String home(){
        return "Hello, World";
    }

    @RequestMapping(path="/login")
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

    @RequestMapping(path="/register")
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
}
