package com.chelms.restaurantexplorer.model.google_api_objects;

public class NearbySearchRequest {
    private LocationRestriction locationRestriction;
    private String[] includedTypes;

    public NearbySearchRequest(LocationRestriction locationRestriction, String[] includedTypes) {
        this.locationRestriction = locationRestriction;
        this.includedTypes = includedTypes;
    }

    public LocationRestriction getLocationRestriction() {
        return locationRestriction;
    }

    public void setLocationRestriction(LocationRestriction locationRestriction) {
        this.locationRestriction = locationRestriction;
    }

    public String[] getIncludedTypes() {
        return includedTypes;
    }

    public void setIncludedTypes(String[] includedTypes) {
        this.includedTypes = includedTypes;
    }

    public static class LocationRestriction {
        private Circle circle;

        public LocationRestriction(Circle circle) {
            this.circle = circle;
        }

        public Circle getCircle() {
            return circle;
        }

        public void setCircle(Circle circle) {
            this.circle = circle;
        }

        public static class Circle {
            private Center center;
            private double radius;

            public Circle(Center center, double radius) {
                this.center = center;
                this.radius = radius;
            }

            public Center getCenter() {
                return center;
            }

            public void setCenter(Center center) {
                this.center = center;
            }

            public double getRadius() {
                return radius;
            }

            public void setRadius(double radius) {
                this.radius = radius;
            }

            public static class Center {
                private double latitude;
                private double longitude;

                public Center(double latitude, double longitude) {
                    this.latitude = latitude;
                    this.longitude = longitude;
                }

                public double getLatitude() {
                    return latitude;
                }

                public void setLatitude(double latitude) {
                    this.latitude = latitude;
                }

                public double getLongitude() {
                    return longitude;
                }

                public void setLongitude(double longitude) {
                    this.longitude = longitude;
                }
            }
        }
    }
}