package ru.chaos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GoodbyeController {

    @GetMapping("/goodbye")
    public String sayGoodbye(){
        return "Goodbye!";
    }
}
