package com.github.valeryad.javacollections.actions;

import com.github.valeryad.javacollections.carfeatures.ComfortLevel;
import com.github.valeryad.javacollections.cars.BusinessClassTaxi;
import com.github.valeryad.javacollections.cars.FreightTaxi;
import com.github.valeryad.javacollections.cars.RegularTaxi;
import com.github.valeryad.javacollections.cars.Vehicle;

import java.util.*;

public class CarCreator {
    private static final String BUSINESS_CLASS_TAXI = "BusinessClassTaxi";
    private static final String FREIGHT_TAXI = "FreightTaxi";
    private static final String REGULAR_TAXI = "RegularTaxi";
    private static final int LOAD_CAPACITY = 2200;

    private PropertiesLoader propertiesLoader;


    public CarCreator() {
        propertiesLoader = new PropertiesLoader();
    }

    public List<Vehicle> createVehicles() {
        List<Vehicle> vehicles = new LinkedList<>();
        Properties carModelsByMake = propertiesLoader.getAllCarModelsByMakes();
        Properties carAmount = propertiesLoader.getCarsAmount();

        for (Object model : carModelsByMake.keySet()) {

            String make = (String) carModelsByMake.get(model);
            int amount = Integer.parseInt((String) carAmount.get(model));
            vehicles.addAll(createVehicleGroup((String) model, make, amount));
        }

        return vehicles;
    }

    private List<Vehicle> createVehicleGroup(String model, String make, int amount) {
        List<Vehicle> vehicles = new LinkedList<>();
        Properties carTypes = propertiesLoader.getModelsByType();

        for (int i = 0; i < amount; i++) {
            if (carTypes.get(model).equals(REGULAR_TAXI)) {
                RegularTaxi regularTaxi = createRegularTaxi(model, make);
                vehicles.add(regularTaxi);
            } else if (carTypes.get(model).equals(FREIGHT_TAXI)) {
                FreightTaxi freightTaxi = createFreightTaxi(model, make);
                vehicles.add(freightTaxi);
            } else {
                BusinessClassTaxi businessClassTaxi = createBusinessClassTaxi(model, make);
                vehicles.add(businessClassTaxi);
            }
        }
        return vehicles;
    }

    private RegularTaxi createRegularTaxi(String model, String make) {
        RegularTaxi regularTaxi = new RegularTaxi(make, model);
        initVehicle(regularTaxi, model);
        regularTaxi.setPassengerCapacity(Integer.parseInt((String) propertiesLoader.getPassengerCapacity().get(model)));
        return regularTaxi;
    }

    private FreightTaxi createFreightTaxi(String model, String make) {
        FreightTaxi freightTaxi = new FreightTaxi(make, model);
        initVehicle(freightTaxi, model);
        freightTaxi.setLoadCapacity(LOAD_CAPACITY);
        return freightTaxi;
    }

    private BusinessClassTaxi createBusinessClassTaxi(String model, String make) {
        BusinessClassTaxi businessClassTaxi = new BusinessClassTaxi(make, model);
        initVehicle(businessClassTaxi, model);
        if (businessClassTaxi.getMake().equals("Mercedes")) {
            businessClassTaxi.setComfortLevel(ComfortLevel.VIP);
        } else {
            businessClassTaxi.setComfortLevel(ComfortLevel.BUSINESS);
        }
        return businessClassTaxi;
    }

    private void initVehicle(Vehicle vehicle, String model) {
        vehicle.setFuelConsumption(Double.parseDouble((String) propertiesLoader.getFuelConsumption().get(model)));
        vehicle.setMaxSpeed(Integer.parseInt((String) propertiesLoader.getMaxSpeed().get(model)));
    }

}
