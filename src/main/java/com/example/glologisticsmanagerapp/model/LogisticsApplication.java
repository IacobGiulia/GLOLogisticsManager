package com.example.glologisticsmanagerapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void createShipment(Shipment shipment) {
        if (shipment.getTrackingNumber() == null || shipment.getTrackingNumber().isEmpty()) {
            shipment.setTrackingNumber(generateUniqueTrackingNumber());
        }
        shipments.add(shipment);
        System.out.println("Shipment created with tracking: " + shipment.getTrackingNumber());
    }

    public Shipment getShipmentByTrackingNumber(String trackingNumber) {
        return shipments.stream()
                .filter(s -> s.getTrackingNumber().equals(trackingNumber))
                .findFirst()
                .orElse(null);
    }

    public List<Shipment> getShipmentsForCustomer(Customer customer) {
        return shipments.stream()
                .filter(s -> s.getReceiver().equals(customer.getContactInfo()))
                .collect(Collectors.toList());
    }

    public String generateUniqueTrackingNumber() {
        String tracking;
        do {
            tracking = "TRK-" + (int)(Math.random() * 1000000);
        } while (getShipmentByTrackingNumber(tracking) != null);
        return tracking;
    }

    public void printShipmentDetails(String trackingNumber) {
        Shipment shipment = getShipmentByTrackingNumber(trackingNumber);
        if (shipment == null) {
            System.out.println("No shipment found with tracking number: " + trackingNumber);
        } else {
            shipment.printDetails();
        }
    }

    public void assignCarrier(Shipment shipment, Carrier carrier) {
        if (shipments.contains(shipment) && carrier != null) {
            shipment.setCarrier(carrier);
        }
    }

    public void allocateWarehouse(Shipment shipment) {

        if (!warehouses.isEmpty()) {
            shipment.setWarehouse(warehouses.get(0));
        }
    }

    public void createRoute(Route route) {
        if (route != null) {
            routes.add(route);
        }
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicle != null) {
            fleet.add(vehicle);
        }
    }

    public void removeVehicle(Vehicle vehicle) {
        fleet.remove(vehicle);
    }

    public void addCustomer(Customer customer) {
        if (customer != null) {
            customers.add(customer);
        }
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

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
