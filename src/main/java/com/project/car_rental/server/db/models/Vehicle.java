package com.project.car_rental.server.db.models;

import jakarta.persistence.*;

/**
 * Entity class representing a vehicle in the Car Rental application.
 */
@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Transient
    private String classType = "Vehicle";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleID")
    private Integer vehicleID;

    @Column(name = "Brand")
    private String brand;

    @Column(name = "Model")
    private String model;

    @Column(name = "VehicleClass")
    private String vehicleClass;

    @Column(name = "YearOfProduction")
    private Integer yearOfProduction;

    @Column(name = "Transmission")
    private String transmission;

    @Column(name = "Horsepower")
    private Integer horsepower;

    @Column(name = "FuelConsumption")
    private Double fuelConsumption;

    @Column(name = "FuelType")
    private String fuelType;

    @Column(name = "PricePerDay")
    private Double pricePerDay;

    /**
     * Gets the class type of the vehicle.
     *
     * @return The class type of the vehicle.
     */
    public String getClassType() {
        return classType;
    }

    /**
     * Sets the class type of the vehicle.
     *
     * @param classType The class type to set.
     */
    public void setClassType(String classType) {
        this.classType = classType;
    }

    /**
     * Gets the ID of the vehicle.
     *
     * @return The ID of the vehicle.
     */
    public Integer getVehicleID() {
        return vehicleID;
    }

    /**
     * Sets the ID of the vehicle.
     *
     * @param vehicleID The ID to set.
     */
    public void setVehicleID(Integer vehicleID) {
        this.vehicleID = vehicleID;
    }

    /**
     * Gets the brand of the vehicle.
     *
     * @return The brand of the vehicle.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand of the vehicle.
     *
     * @param brand The brand to set.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets the model of the vehicle.
     *
     * @return The model of the vehicle.
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model of the vehicle.
     *
     * @param model The model to set.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the class of the vehicle.
     *
     * @return The class of the vehicle.
     */
    public String getVehicleClass() {
        return vehicleClass;
    }

    /**
     * Sets the class of the vehicle.
     *
     * @param vehicleClass The class to set.
     */
    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    /**
     * Gets the year of production of the vehicle.
     *
     * @return The year of production of the vehicle.
     */
    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    /**
     * Sets the year of production of the vehicle.
     *
     * @param yearOfProduction The year of production to set.
     */
    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    /**
     * Gets the transmission of the vehicle.
     *
     * @return The transmission of the vehicle.
     */
    public String getTransmission() {
        return transmission;
    }

    /**
     * Sets the transmission of the vehicle.
     *
     * @param transmission The transmission to set.
     */
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    /**
     * Gets the horsepower of the vehicle.
     *
     * @return The horsepower of the vehicle.
     */
    public Integer getHorsepower() {
        return horsepower;
    }

    /**
     * Sets the horsepower of the vehicle.
     *
     * @param horsepower The horsepower to set.
     */
    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    /**
     * Gets the fuel consumption of the vehicle.
     *
     * @return The fuel consumption of the vehicle.
     */
    public Double getFuelConsumption() {
        return fuelConsumption;
    }

    /**
     * Sets the fuel consumption of the vehicle.
     *
     * @param fuelConsumption The fuel consumption to set.
     */
    public void setFuelConsumption(Double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    /**
     * Gets the fuel type of the vehicle.
     *
     * @return The fuel type of the vehicle.
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * Sets the fuel type of the vehicle.
     *
     * @param fuelType The fuel type to set.
     */
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * Gets the price per day of the vehicle.
     *
     * @return The price per day of the vehicle.
     */
    public Double getPricePerDay() {
        return pricePerDay;
    }

    /**
     * Sets the price per day of the vehicle.
     *
     * @param pricePerDay The price per day to set.
     */
    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    /**
     * Returns a string representation of the vehicle.
     *
     * @return A string representation of the vehicle.
     */
    @Override
    public String toString() {
        return "Vehicle{" +
                "classType='" + classType + '\'' +
                ", vehicleID=" + vehicleID +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", vehicleClass='" + vehicleClass + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                ", transmission='" + transmission + '\'' +
                ", horsepower=" + horsepower +
                ", fuelConsumption=" + fuelConsumption +
                ", fuelType='" + fuelType + '\'' +
                ", pricePerDay=" + pricePerDay +
                '}';
    }
}
