package com.example.glologisticsmanagerapp.model;

import java.util.ArrayList;
import java.util.List;

public class LogisticsApplication {

    private List<Shipment> shipments;
    private List<Warehouse> warehouses;
    private List<Carrier> carriers;
    private List<Route> routes;
    private List<Vehicle> fleet;
    private List<Customer> customers;

    public LogisticsApplication() {
        this.shipments = new ArrayList<>();
        this.warehouses = new ArrayList<>();
        this.carriers = new ArrayList<>();
        this.routes = new ArrayList<>();
        this.fleet = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    // --- Shipment methods ---
    public void createShipment(Shipment shipment) {
        if (shipment != null) {
            shipments.add(shipment);
        }
    }

    public void assignCarrier(Shipment shipment, Carrier carrier) {
        if (shipments.contains(shipment) && carrier != null) {
            shipment.setCarrier(carrier);
        }
    }

    public void allocateWarehouse(Shipment shipment) {
        // Simplificat: aloca primul warehouse disponibil
        if (!warehouses.isEmpty()) {
            shipment.setWarehouse(warehouses.get(0));
        }
    }

    // --- Route methods ---
    public void createRoute(Route route) {
        if (route != null) {
            routes.add(route);
        }
    }

    // --- Fleet methods ---
    public void addVehicle(Vehicle vehicle) {
        if (vehicle != null) {
            fleet.add(vehicle);
        }
    }

    public void removeVehicle(Vehicle vehicle) {
        fleet.remove(vehicle);
    }

    // --- Customer methods ---
    public void addCustomer(Customer customer) {
        if (customer != null) {
            customers.add(customer);
        }
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    // --- Getters ---
    public List<Shipment> getShipments() {
        return shipments;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public List<Carrier> getCarriers() {
        return carriers;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public List<Vehicle> getFleet() {
        return fleet;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
