package com.github.valeryad.javacollections.actions;

import java.io.*;
import java.util.*;

public class PropertiesLoader {

    private static final String PATH_PRICES = "data/prices.properties";
    private static final String PATH_CARS_AMOUNT = "data/amount.properties";
    private static final String PATH_CAR_MODELS_BY_MAKES = "data/models.properties";
    private static final String PATH_CAR_MODELS_BY_TYPES = "data/types.properties";
    private static final String PATH_PASSENGER_CAPACITY_PROPERTIES = "data/passengercapacity.properties";
    private static final String PATH_CONSUMPTION_PROPERTIES = "data/consumption.properties";
    private static final String PATH_MAXSPEED_PROPERTIES = "data/maxspeed.properties";

    private static Properties properties;
    private static PropertiesLoader instance;

    private PropertiesLoader(){
        properties = new Properties();
    }

    public static PropertiesLoader getInstance(){
        if (instance == null){
            instance = new PropertiesLoader();
        }
        return instance;
    }

    private Properties loadProperties(String path) {
        File file = new File(path);
        try (InputStream inputStream = new FileInputStream(file)) {
            properties.load(inputStream);
        } catch (java.io.FileNotFoundException e) {
            System.err.println("Can't find file " + path);
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public Properties getAllCarModelsByMakes() {
        return loadProperties(PATH_CAR_MODELS_BY_MAKES);
    }

    public Properties getModelsByType() {
        return loadProperties(PATH_CAR_MODELS_BY_TYPES);
    }

    public Properties getPrices() {
        return loadProperties(PATH_PRICES);
    }

    public Properties getCarsAmount() {
        return loadProperties(PATH_CARS_AMOUNT);
    }

    public Properties getPassengerCapacity() {
        return loadProperties(PATH_PASSENGER_CAPACITY_PROPERTIES);
    }

    public Properties getFuelConsumption() {
        return loadProperties(PATH_CONSUMPTION_PROPERTIES);
    }

    public Properties getMaxSpeed() {
        return loadProperties(PATH_MAXSPEED_PROPERTIES);
    }
}