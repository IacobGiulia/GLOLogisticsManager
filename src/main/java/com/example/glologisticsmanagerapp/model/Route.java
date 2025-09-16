package com.example.glologisticsmanagerapp.model;

import java.time.LocalDateTime;

public class Route {
    private Location origin;
    private Location destination;
    private float distance;

    public Route(Location origin, Location destination, float distance) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }
    public Location getOrigin() {
        return origin;
    }
    public void setOrigin(Location origin) {
        this.origin = origin;
    }
    public Location getDestination() {
        return destination;
    }
    public void setDestination(Location destination) {
        this.destination = destination;
    }
    public float getDistance() {
        return distance;
    }
    public void setDistance(float distance) {
        this.distance = distance;
    }

    public LocalDateTime calculateETA(){
        return null;
    }

    @Override
    public String toString() {
        return "Route{" +
                "origin=" + origin +
                ", destination=" + destination +
                ", distance=" + distance +
                '}';
    }
}
