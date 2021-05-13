package com.github.valeryad.javacollections.cars;


import com.github.valeryad.javacollections.carfeatures.Status;

public abstract class Vehicle {

    private static int carAmount;

    private int id;
    private String make;
    private String model;
    private double fuelConsumption;
    private int maxSpeed;
    private Status status;

    static {
        carAmount = 0;
    }

    public Vehicle() {
        id = ++carAmount;
        status = Status.OFFLINE;
    }

    public Vehicle(String make, String model) {
        id = ++carAmount;
        status = Status.OFFLINE;
        this.make = make;
        this.model = model;
    }

    public Vehicle(String make, String model, int fuelConsumption, int maxSpeed) {
        id = ++carAmount;
        this.make = make;
        this.model = model;
        this.fuelConsumption = fuelConsumption;
        this.maxSpeed = maxSpeed;

    }

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return id * 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj.getClass() != this.getClass()) return false;
        Vehicle car = (Vehicle) obj;
        return id == car.getId();
    }

    @Override
    public String toString() {
        return String.format("%d : %s %s", id, make, model);
    }
}
