package com.spot.me.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpotmeController {
    @RequestMapping(path="/")
    public String home(){
        return "";
    }
}
