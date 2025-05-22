package com.chelms.restaurantexplorer.controller;

import org.springframework.web.bind.annotation.RestController;

import com.chelms.restaurantexplorer.service.NearbySearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class NearbySearchController {
    @Autowired
    private NearbySearchService nearbySearchService;

    @GetMapping("/search")
    public String index() {
        return nearbySearchService.searchNearby(35.943983, -78.87182609999999, 40233., new String[]{"american_restaurant", "asian_restaurant"});
    }
}
