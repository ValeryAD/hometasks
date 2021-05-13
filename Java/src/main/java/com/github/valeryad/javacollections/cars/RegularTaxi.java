package com.github.valeryad.javacollections.cars;


public class RegularTaxi extends Vehicle {

    int passengerCapacity;

    public RegularTaxi() {
    }

    public RegularTaxi(String make, String model) {
        super(make, model);
    }

    public RegularTaxi(String make, String model, int fuelConsumption, int maxSpeed, int passengerCapacity) {
        super(make, model, fuelConsumption, maxSpeed);
        this.passengerCapacity = passengerCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
}
