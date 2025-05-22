package com.chelms.restaurantexplorer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for Google Places API.
 * <p>
 * This class is used to bind the properties defined in the application.properties file
 * with the prefix "google.places.api" to the fields in this class.
 * </p>
 */
@Component
@ConfigurationProperties(prefix = "google.places.api")
public class GooglePlacesProperties {
    private String key;
    private String url;
    private String fieldMask;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFieldMask() {
        return fieldMask;
    }

    public void setFieldMask(String fieldMask) {
        this.fieldMask = fieldMask;
    }
}