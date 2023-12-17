package com.project.car_rental.client.db_models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    private String classType = "Vehicle";

    @Id
    @Column(name = "VehicleID")
    private int vehicleID;

    @Column(name = "Brand")
    private String brand;

    @Column(name = "Model")
    private String model;

    @Column(name = "VehicleClass")
    private String vehicleClass;

    @Column(name = "YearOfProduction")
    private int yearOfProduction;

    @Column(name = "Transmission")
    private String transmission;

    @Column(name = "Horsepower")
    private int horsepower;

    @Column(name = "FuelConsumption")
    private double fuelConsumption;

    @Column(name = "FuelType")
    private String fuelType;

    @Column(name = "PricePerDay")
    private double pricePerDay;

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

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