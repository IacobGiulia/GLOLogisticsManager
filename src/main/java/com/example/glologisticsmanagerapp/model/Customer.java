package com.example.glologisticsmanagerapp.model;

public class Customer {
    private String name;
    private Contact contactInfo;

    public Customer(String name, Contact contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Contact getContactInfo() {
        return contactInfo;
    }
    public void setContactInfo(Contact contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", contactInfo=" + contactInfo +
                '}';
    }
}
