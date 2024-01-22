package com.project.car_rental.server.db.models;

import jakarta.persistence.*;

import java.util.Date;

/**
 * This class represents a reservation in the car rental system.
 */
@Entity
@Table(name = "reservation")
public class Reservation {

    @Transient
    private String classType = "Reservation";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReservationID")
    private Integer reservationID;

    @Column(name = "CustomerID")
    private Integer customerID;

    @Column(name = "CustomerName")
    private String customerName;

    @Column(name = "VehicleID")
    private Integer vehicleID;

    @Column(name = "VehicleName")
    private String vehicleName;

    @Column(name = "RentalDate")
    private Date rentalDate;

    @Column(name = "ReturnDate")
    private Date returnDate;

    @Column(name = "TotalCost")
    private Double totalCost;

    /**
     * Gets the class type.
     *
     * @return The class type.
     */
    public String getClassType() {
        return classType;
    }

    /**
     * Sets the class type.
     *
     * @param classType The class type.
     */
    public void setClassType(String classType) {
        this.classType = classType;
    }

    /**
     * Gets the reservation ID.
     *
     * @return The reservation ID.
     */
    public Integer getReservationID() {
        return reservationID;
    }

    /**
     * Sets the reservation ID.
     *
     * @param reservationID The reservation ID.
     */
    public void setReservationID(Integer reservationID) {
        this.reservationID = reservationID;
    }

    /**
     * Gets the customer ID.
     *
     * @return The customer ID.
     */
    public Integer getCustomerID() {
        return customerID;
    }

    /**
     * Sets the customer ID.
     *
     * @param customerID The customer ID.
     */
    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    /**
     * Gets the customer name.
     *
     * @return The customer name.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the customer name.
     *
     * @param customerName The customer name.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the vehicle ID.
     *
     * @return The vehicle ID.
     */
    public Integer getVehicleID() {
        return vehicleID;
    }

    /**
     * Sets the vehicle ID.
     *
     * @param vehicleID The vehicle ID.
     */
    public void setVehicleID(Integer vehicleID) {
        this.vehicleID = vehicleID;
    }

    /**
     * Gets the vehicle name.
     *
     * @return The vehicle name.
     */
    public String getVehicleName() {
        return vehicleName;
    }

    /**
     * Sets the vehicle name.
     *
     * @param vehicleName The vehicle name.
     */
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    /**
     * Gets the rental date.
     *
     * @return The rental date.
     */
    public Date getRentalDate() {
        return rentalDate;
    }

    /**
     * Sets the rental date.
     *
     * @param rentalDate The rental date.
     */
    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    /**
     * Gets the return date.
     *
     * @return The return date.
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the return date.
     *
     * @param returnDate The return date.
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Gets the total cost.
     *
     * @return The total cost.
     */
    public Double getTotalCost() {
        return totalCost;
    }

    /**
     * Sets the total cost.
     *
     * @param totalCost The total cost.
     */
    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Returns a string representation of the reservation.
     *
     * @return A string representation of the reservation.
     */
    @Override
    public String toString() {
        return "Reservation{" +
                "classType='" + classType + '\'' +
                ", reservationID=" + reservationID +
                ", customerID=" + customerID +
                ", customerName='" + customerName + '\'' +
                ", vehicleID=" + vehicleID +
                ", vehicleName='" + vehicleName + '\'' +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", totalCost=" + totalCost +
                '}';
    }
}
