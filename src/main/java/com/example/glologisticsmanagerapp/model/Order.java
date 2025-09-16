package com.example.glologisticsmanagerapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private String orderNumber;
    private Customer customer;
    private List<OrderItem> items;

    public Order(String orderNumber, Customer customer) {
        this.orderNumber = Objects.requireNonNull(orderNumber, "Order number cannot be null");
        this.customer = Objects.requireNonNull(customer, "Customer cannot be null");
        this.items = new ArrayList<>();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItem(OrderItem item) {
        if (item != null) {
            items.add(item);
        }
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber='" + orderNumber + '\'' +
                ", customer=" + customer +
                ", items=" + items +
                '}';
    }

}
