package com.oop.voyage.project_voyage.model;

public class Car extends Vehicle {

    public Car(String vehicleType, String numberPlate) {
        super(vehicleType, numberPlate, seatsForType(vehicleType));
    }

    private static int seatsForType(String type) {
        if (type == null) return 4;
        return switch (type) {
            case "Sedan / Toyota (5 seater)"   -> 5;
            case "SUV / Crossover (7 seater)"  -> 7;
            case "Van / Minivan"               -> 10;
            case "Mazda / Bus"                 -> 14;
            case "DoubleDecker Bus"            -> 60;
            case "Motorcycle"                  -> 1;
            default                            -> 4;
        };
    }

    @Override
    public int getSeatsFromType(String type) {
        return seatsForType(type);
    }
}