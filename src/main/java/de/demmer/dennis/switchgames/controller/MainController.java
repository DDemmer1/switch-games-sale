package de.demmer.dennis.switchgames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping(value ="/")
    public String index(){
        return "Hello World!";
    }
}
