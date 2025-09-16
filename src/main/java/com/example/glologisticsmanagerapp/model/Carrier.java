package com.example.glologisticsmanagerapp.model;

public class Carrier {
    private String name;
    private Contact contactInfo;
    private Rates rates;

    public Carrier(String name, Contact contactInfo) {
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

    public Rates getShippingRates(){
        return rates;
    }

    @Override
    public String toString() {
        return "Carrier{" +
                "name='" + name + '\'' +
                ", contactInfo=" + contactInfo +
                '}';
    }
}
