package com.cricket.simulator.matches.constants;

public enum WeatherStatus {

    SUNNY("SUNNY"),
    COLD("COLD"),
    RAINY("RAINY");

    private final String value;

    // Constructor to initialize the value
    WeatherStatus (String value) {
        this.value = value;
    }

    // Getter method to retrieve the value
    public String getValue() {
        return value;
    }
}
