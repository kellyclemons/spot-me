package com.spot.me.controllers;

import com.spot.me.entities.User;
import com.spot.me.services.UserRepository;
import com.spot.me.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class SpotmeController {
    @Autowired
    UserRepository users;

    @RequestMapping(path="/")
    public String home(){
        return "Hello, World";
    }

    @RequestMapping(path="/login")
    public User login(String email, String password, HttpServletResponse response) throws Exception {
        User user = users.findFirstByEmail(email);
        if(user == null) {
            return user;
        }else if (! PasswordStorage.verifyPassword(password,user.getPassword())) {
            response.sendError(401, "Invalid Credentials");
        }
        return user;
    }

    @RequestMapping(path="/register")
    public User Register(String email, String name, String password, HttpServletResponse response) throws Exception {
        User user = users.findFirstByEmail(email);
        if(user != null) {
            response.sendError(422, "Username is taken.");
        }else{
            user = new User(email, name, password);
            users.save(user);
        }

        return user;
    }
}
