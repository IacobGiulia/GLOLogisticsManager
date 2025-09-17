package com.example.glologisticsmanagerapp.model;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.util.Objects;


public class Shipment {
    private Address sender;
    private Address receiver;
    private float weight;
    private Status status;
    private Carrier carrier;
    private Warehouse warehouse;
    private String trackingNumber;

    public Shipment(Address sender, Address receiver, float weight, Status status,
                    Carrier carrier, Warehouse warehouse, String trackingNumber) {
        this.sender = Objects.requireNonNull(sender, "Sender cannot be null");
        this.receiver = Objects.requireNonNull(receiver, "Receiver cannot be null");
        this.weight = weight;
        this.status = Objects.requireNonNull(status, "Status cannot be null");
        this.carrier = Objects.requireNonNull(carrier, "Carrier cannot be null");
        this.warehouse = Objects.requireNonNull(warehouse, "Warehouse cannot be null");
        this.trackingNumber = Objects.requireNonNull(trackingNumber, "TrackingNumber cannot be null");
    }

    public Address getSender() { return sender; }
    public Address getReceiver() { return receiver; }
    public float getWeight() { return weight; }
    public Status getStatus() { return status; }
    public Carrier getCarrier() { return carrier; }
    public Warehouse getWarehouse() { return warehouse; }

    public void setSender(Address sender) { this.sender = sender; }
    public void setReceiver(Address receiver) { this.receiver = receiver; }
    public void setWeight(float weight) { this.weight = weight; }
    public void setCarrier(Carrier carrier) { this.carrier = carrier; }
    public void setWarehouse(Warehouse warehouse) { this.warehouse = warehouse; }

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }


    public void updateStatus(Status newStatus) {
        if(newStatus == null)
            throw new IllegalArgumentException("Status cannot be null");
        this.status = newStatus;
        System.out.println("Shipment status updated to: " + newStatus.getDescription());

    }

    public float calculateShippingCost(){
        if(carrier == null || carrier.getShippingRates() == null){
            throw new IllegalArgumentException("Carrier or rates not set");
        }
        Rates rates = carrier.getShippingRates();
        float cost = weight * rates.getGround();
        return cost;

    }

    public float calculateTotalWeight(){
        if (warehouse == null || warehouse.getInventory().isEmpty()) {
            return 0;
        }
        return warehouse.getInventory().stream()
                .mapToInt(Item::getQuantity)
                .sum();
    }

    public String generateShipmentLabel() {
        return "Shipment Label\n" +
                "From: " + sender + "\n" +
                "To: " + receiver + "\n" +
                "Weight: " + weight + " kg\n" +
                "Status: " + status + "\n" +
                "Tracking: " + (trackingNumber != null ? trackingNumber : "Not assigned") + "\n" +
                "Carrier: " + (carrier != null ? carrier.getName() : "Not assigned") + "\n" +
                "Warehouse: " + (warehouse != null ? warehouse.getLocation() : "Not assigned");
    }

    public void printDetails() {
        System.out.println("Shipment Details:");
        System.out.println("From: " + sender);
        System.out.println("To: " + receiver);
        System.out.println("Weight: " + weight);
        System.out.println("Status: " + status);
        System.out.println("Tracking Number: " + trackingNumber);
        System.out.println("Carrier: " + (carrier != null ? carrier.getName() : "Not assigned"));
        System.out.println("Warehouse: " + (warehouse != null ? warehouse.getLocation() : "Not assigned"));
    }


    @Override
    public String toString() {
        return "Shipment{" +
                "sender=" + sender +
                ", receiver=" + receiver +
                ", weight=" + weight +
                ", status=" + status +
                ", carrier=" + carrier +
                ", warehouse=" + warehouse +
                '}';
    }
}
