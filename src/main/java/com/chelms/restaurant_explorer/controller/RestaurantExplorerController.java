package com.chelms.restaurant_explorer.controller;

import org.springframework.web.bind.annotation.RestController;

import com.chelms.restaurant_explorer.service.NearbySearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class RestaurantExplorerController {
    @Autowired
    private NearbySearchService nearbySearchService;
    @GetMapping("/")
    public String index() {
        return nearbySearchService.searchNearby(35.943983, -78.87182609999999, 40233.6);
    }
}
