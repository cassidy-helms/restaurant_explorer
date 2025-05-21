package com.chelms.restaurant_explorer.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class RestaurantExplorerController {
    @GetMapping("/")
    public String index() {
        return "Greetings from Restaurant Explorer!";
    }
}
