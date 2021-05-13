package com.github.valeryad.javacollections;

import com.github.valeryad.javacollections.actions.CarCreator;
import com.github.valeryad.javacollections.actions.TaxiStation;
import com.github.valeryad.javacollections.cars.Vehicle;

import java.util.Scanner;

public class Runner {

    private static final String GREETING = "Hello!";
    private static final String MAIN_MENU = "\nPlease, choose item to execute:\n" +
            "1 - Count value of all cars in the company\n" +
            "2 - Sort cars by fuel consumption and print\n" +
            "3 - Find a car according specified range of speed\n" +
            "0 - For exit\n";
    private static final String WRONG_MENU_ITEM_INPUT_MESSAGE = "Please, type in integer value from 0 to 3\n";
    private static final String VEHICLES_VALUE_REPORT = "All vehicles of the taxi station costs: ";
    private static final String SPEED_RANGE_REQUEST = "Type in consistently two integer, representing speed range.\n" +
            "Please note that the min and max values of maximum speed of vehicles in the taxi station are:\n" +
            "%d and %d.\n" +
            "You may type in values between them inclusively.\n";
    private static final String WRONG_SPEED_VALUE_MESSAGE = "Please type in integer values between %d and %d";
    private static final String NO_SUCH_VEHICLE_MESSAGE = "There's no vehicle with maximum speed withing specified range in the taxi station";
    private static final String VEHICLE_CONSUMPTION_REPORT = "%04d %12s %10s, fuel consumption = %3.1f l/100km\n";
    private static final String VEHICLE_SPECIFIED_SPEED_REPORT = "\n%04d %s %s, max speed = %3d km/h\n";

    private static Scanner scanner;
    private static TaxiStation taxiStation;

    private static class Range {
        private int min;
        private int max;

        Range(int min, int max) {
            this.min = Math.min(min, max);
            this.max = Math.max(min, max);
        }
    }

    static {
        scanner = new Scanner(System.in);
        taxiStation = new TaxiStation(new CarCreator().createVehicles());
    }


    public static void main(String[] args) {

        int choice = 0;
        System.out.println(GREETING);
        System.out.println(MAIN_MENU);

        do {
            choice = askUserForChoice(WRONG_MENU_ITEM_INPUT_MESSAGE);

            switch (choice) {
                case 1:
                    printVehiclesValue();
                    System.out.println(MAIN_MENU);
                    break;
                case 2:
                    printSortedCarsByConsumption();
                    System.out.println(MAIN_MENU);
                    break;
                case 3:
                    findCarBySpeedRange();
                    System.out.println(MAIN_MENU);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println(WRONG_MENU_ITEM_INPUT_MESSAGE);
            }
        } while (true);

    }

    private static int askUserForChoice(String messageIfWrongInput) {
        int choice = 0;
        while (!scanner.hasNextInt()) {
            System.out.println(messageIfWrongInput);
            scanner.nextLine();
        }

        choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private static void printVehiclesValue() {
        System.out.println(VEHICLES_VALUE_REPORT + taxiStation.countVehiclesValue());
    }

    private static void printSortedCarsByConsumption() {
        taxiStation.getVehicles().stream()
                .sorted((v1, v2) -> Double.compare(v1.getFuelConsumption(), v2.getFuelConsumption()))
                .forEach(v -> System.out.printf(VEHICLE_CONSUMPTION_REPORT,
                        v.getId(),
                        v.getMake(),
                        v.getModel(),
                        v.getFuelConsumption()));
    }

    private static void findCarBySpeedRange() {
        int minSpeedOfAllVehicles = taxiStation.getLowestMaxSpeedOfAllVehicles();
        int maxSpeedOfAllVehicles = taxiStation.getHighestMaxSpeedOfAllVehicles();

        Range range;

        System.out.printf(SPEED_RANGE_REQUEST, minSpeedOfAllVehicles, maxSpeedOfAllVehicles);

        do {
            range = askUserForSpeedRange(String.format(WRONG_SPEED_VALUE_MESSAGE, minSpeedOfAllVehicles, maxSpeedOfAllVehicles));

            if (range.min < minSpeedOfAllVehicles || range.max > maxSpeedOfAllVehicles) {
                System.out.printf(WRONG_SPEED_VALUE_MESSAGE, minSpeedOfAllVehicles, maxSpeedOfAllVehicles);
                continue;
            }

            break;
        } while (true);

        Vehicle vehicle = taxiStation.findVehicleBySpeedRange(range.min, range.max);

        if (vehicle == null) {
            System.out.println(NO_SUCH_VEHICLE_MESSAGE);
            return;
        }

        System.out.printf(VEHICLE_SPECIFIED_SPEED_REPORT,
                vehicle.getId(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getMaxSpeed());
    }

    private static Range askUserForSpeedRange(String wrongInputMessage) {
        int min = askUserForChoice(wrongInputMessage);
        int max = askUserForChoice(wrongInputMessage);

        return new Range(min, max);
    }
}
