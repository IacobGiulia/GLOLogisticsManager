package com.example.glologisticsmanagerapp.model;
/**
 * Represents a vehicle used for transporting shipments.
 */
public class Vehicle {
    private String licensePlate;
    private String type; // e.g., Truck, Van, Bike
    private float capacity; // in kg

    public Vehicle(String licensePlate, String type, float capacity) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.capacity = capacity;
    }

    // --- Getters & Setters ---
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "licensePlate='" + licensePlate + '\'' +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}