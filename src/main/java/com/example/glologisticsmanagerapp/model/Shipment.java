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

    public Shipment(Address sender, Address receiver, float weight, Status status,
                    Carrier carrier, Warehouse warehouse) {
        this.sender = Objects.requireNonNull(sender, "Sender cannot be null");
        this.receiver = Objects.requireNonNull(receiver, "Receiver cannot be null");
        this.weight = weight;
        this.status = Objects.requireNonNull(status, "Status cannot be null");
        this.carrier = Objects.requireNonNull(carrier, "Carrier cannot be null");
        this.warehouse = Objects.requireNonNull(warehouse, "Warehouse cannot be null");
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
