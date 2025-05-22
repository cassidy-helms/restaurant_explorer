package com.chelms.restaurant_explorer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.chelms.restaurant_explorer.model.google_api_objects.NearbySearchRequest;


@Service
public class NearbySearchService {
    private final String apiKey;
    private final String baseUrl;

    public NearbySearchService(
        @Value("${google.places.api.key}") String apiKey,
        @Value("${google.places.api.url}") String baseUrl
    ) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    public String searchNearby(double lat, double lng, double radius) {
        RestTemplate restTemplate = new RestTemplate();

        var center = new NearbySearchRequest.LocationRestriction.Circle.Center(lat, lng);
        var circle = new NearbySearchRequest.LocationRestriction.Circle(center, radius);
        var locationRestriction = new NearbySearchRequest.LocationRestriction(circle);
        String[] includedTypes = { "restaurant" };
        var requestBody = new NearbySearchRequest(locationRestriction, includedTypes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Goog-Api-Key", apiKey);
        headers.set("X-Goog-FieldMask", "*");

        HttpEntity<NearbySearchRequest> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);

        return response.getBody();
    }
}
