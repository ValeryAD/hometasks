package com.github.valeryad.javacollections.cars;

import com.github.valeryad.javacollections.carfeatures.ComfortLevel;

public class BusinessClassTaxi extends Vehicle {
    private ComfortLevel comfortLevel;

    public BusinessClassTaxi() {
    }

    public BusinessClassTaxi(String make, String model) {
        super(make, model);
    }

    public BusinessClassTaxi(String make, String model, int fuelConsumption, int maxSpeed, ComfortLevel comfortLevel) {
        super(make, model, fuelConsumption, maxSpeed);
        this.comfortLevel = comfortLevel;
    }

    public ComfortLevel getComfortLevel() {
        return comfortLevel;
    }

    public void setComfortLevel(ComfortLevel comfortLevel) {
        this.comfortLevel = comfortLevel;
    }

}
