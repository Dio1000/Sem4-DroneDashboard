package me.dariansandru.domain;

public class DroneData {
    private String name;
    private double longitude;
    private double latitude;

    private boolean fireDetected;
    private double fireProbability;

    private double batteryLevel;
    private double altitude;

    public DroneData() {
        this.name = "Drone";
        this.latitude = -1;
        this.longitude = -1;

        this.fireDetected = false;
        this.fireProbability = -1;

        this.batteryLevel = -1;
        this.altitude = -1;
    }

    public DroneData(String name, double longitude, double latitude,
                     boolean fireDetected, double fireProbability,
                     double batteryLevel, double altitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.fireDetected = fireDetected;
        this.fireProbability = fireProbability;
        this.batteryLevel = batteryLevel;
        this.altitude = altitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public boolean isFireDetected() {
        return fireDetected;
    }

    public void setFireDetected(boolean fireDetected) {
        this.fireDetected = fireDetected;
    }

    public double getFireProbability() {
        return fireProbability;
    }

    public void setFireProbability(double fireProbability) {
        this.fireProbability = fireProbability;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
