package com.project.car_rental.server.db_models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {

    private String classType = "Reservation";

    @Id
    @Column(name = "ReservationID")
    private int reservationID;

    @Column(name = "CustomerID")
    private int customerId;

    @Column(name = "VehicleID")
    private int vehicleId;

    @Column(name = "RentalDate")
    private Date rentalDate;

    @Column(name = "ReturnDate")
    private Date returnDate;

    @Column(name = "TotalCost")
    private double totalCost;

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "classType='" + classType + '\'' +
                ", reservationID=" + reservationID +
                ", customerId=" + customerId +
                ", vehicleId=" + vehicleId +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", totalCost=" + totalCost +
                '}';
    }
}