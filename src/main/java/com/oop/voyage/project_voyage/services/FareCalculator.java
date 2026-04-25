package com.oop.voyage.project_voyage.services;

public class FareCalculator {

    // Base fares in PKR per km by vehicle type
    // Preset Rates being hardcoded as const values
    private static final double RATE_SEDAN      = 25.0;
    private static final double RATE_SUV        = 35.0;
    private static final double RATE_VAN        = 20.0;
    private static final double RATE_BUS        = 12.0;
    private static final double RATE_MOTORCYCLE = 15.0;
    private static final double RATE_DECKER     = 8.0;
    private static final double BASE_FARE       = 50.0; // fixed boarding charge

    public static double calculateFare(double pickupLat, double pickupLng,
                                       double destLat,   double destLng,
                                       String vehicleType) {
        double distKm = ProximityEngine.haversineDistance(
                pickupLat, pickupLng, destLat, destLng) / 1000.0;
        double ratePerKm = getRateForType(vehicleType);
        double fare = BASE_FARE + (distKm * ratePerKm);
        return Math.round(fare * 10.0) / 10.0; // roundoff to 1 decimal digit
    }

    private static double getRateForType(String type) {
        if (type == null) return RATE_SEDAN;
        return switch (type) {
            case "Sedan / Toyota (5 seater)"   -> RATE_SEDAN;
            case "SUV / Crossover (7 seater)"  -> RATE_SUV;
            case "Van / Minivan"               -> RATE_VAN;
            case "Mazda / Bus"                 -> RATE_BUS;
            case "DoubleDecker Bus"            -> RATE_DECKER;
            case "Motorcycle"                  -> RATE_MOTORCYCLE;
            default                            -> RATE_SEDAN;
        };
    }

    public static String formatFare(double fare) {
        return "PKR " + String.format("%.0f", fare);
    }
}