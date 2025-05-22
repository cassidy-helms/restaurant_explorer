package com.chelms.restaurantexplorer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.chelms.restaurantexplorer.config.GooglePlacesProperties;
import com.chelms.restaurantexplorer.model.google_api_objects.NearbySearchRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class NearbySearchService {
    private final GooglePlacesProperties googlePlacesProperties;

    public NearbySearchService(GooglePlacesProperties googlePlacesProperties) {
        this.googlePlacesProperties = googlePlacesProperties;
    }

    /**
     * Searches for places nearby a given latitude and longitude within a specified radius.
     *
     * @param lat           Latitude of the center point.
     * @param lng           Longitude of the center point.
     * @param radius        Search radius in meters.
     * @param includedTypes Array of place types to include in the search.
     * @return JSON string of combined places from all types.
     */
    public String searchNearby(double lat, double lng, double radius, String[] includedTypes) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        List<JsonNode> combinedPlaces = new ArrayList<>();

        for (String type : includedTypes) {
            var center = new NearbySearchRequest.LocationRestriction.Circle.Center(lat, lng);
            var circle = new NearbySearchRequest.LocationRestriction.Circle(center, radius);
            var locationRestriction = new NearbySearchRequest.LocationRestriction(circle);
            var requestBody = new NearbySearchRequest(locationRestriction, new String[]{type});

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("X-Goog-Api-Key", googlePlacesProperties.getKey());
            headers.set("X-Goog-FieldMask", googlePlacesProperties.getFieldMask());

            HttpEntity<NearbySearchRequest> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(googlePlacesProperties.getUrl(), entity, String.class);

            if (response.getBody() != null) {
                try {
                    JsonNode root = objectMapper.readTree(response.getBody());
                    JsonNode places = root.get("places");
                    if (places != null && places.isArray()) {
                        for (JsonNode place : places) {
                            combinedPlaces.add(place);
                        }
                    }
                } catch (Exception e) {
                    // Handle parsing exception (log or rethrow as needed)
                    e.printStackTrace();
                }
            }
        }

        // Return the combined places as a JSON array string
        try {
            return objectMapper.writeValueAsString(combinedPlaces);
        } catch (Exception e) {
            e.printStackTrace();
            return "[]";
        }
    }
}
