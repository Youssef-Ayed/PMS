package com.example.pms.entities;

import java.util.Date;

public class Properties {
    private int id;
    private String name;
    private String location;
    private String description;
    private double pricePerMonth;
    private Date availableFrom;
    private Date availableUntil;
    private String tenantName;
    private String tenantNumber;
    private int userId;

    // Constructors, getters, and setters
    public Properties() {
    }

    public Properties(int id, String name, String location, String description, double pricePerMonth, Date availableFrom, Date availableUntil, String tenantName, String tenantNumber, int userId) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.pricePerMonth = pricePerMonth;
        this.availableFrom = availableFrom;
        this.availableUntil = availableUntil;
        this.tenantName = tenantName;
        this.tenantNumber = tenantNumber;
        this.userId = userId;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Date getAvailableUntil() {
        return availableUntil;
    }

    public void setAvailableUntil(Date availableUntil) {
        this.availableUntil = availableUntil;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantNumber() {
        return tenantNumber;
    }

    public void setTenantNumber(String tenantNumber) {
        this.tenantNumber = tenantNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", userId=" + userId +
                '}';
    }
}

