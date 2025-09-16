package com.example.glologisticsmanagerapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Warehouse {
    private Location location;
    private int capacity;
    private List<Item> inventory;

    public Warehouse(Location location, int capacity) {
        this.location = Objects.requireNonNull(location, "Location cannot be null");
        this.capacity = capacity;
        this.inventory = new ArrayList<>();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public boolean addInventory(Item item) {
        if (inventory.size() >= capacity) {
            System.out.println("Cannot add item: warehouse is full.");
            return false;
        }
        inventory.add(item);
        System.out.println("Item added: " + item.getName());
        return true;
    }

    public boolean removeInventory(Item item) {
        if (inventory.remove(item)) {
            System.out.println("Item removed: " + item.getName());
            return true;
        } else {
            System.out.println("Item not found in warehouse: " + item.getName());
            return false;
        }
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "location=" + location +
                ", capacity=" + capacity +
                ", inventory=" + inventory +
                '}';
    }
}
