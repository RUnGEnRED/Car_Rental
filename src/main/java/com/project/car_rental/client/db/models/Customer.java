package com.project.car_rental.client.db.models;

import jakarta.persistence.*;

/**
 * Entity class representing a customer in the Car Rental application.
 */
@Entity
@Table(name = "customer")
public class Customer {

    @Transient
    private String classType = "Customer";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    private Integer customerID;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Email")
    private String email;

    @Column(name = "Address")
    private String address;

    @Column(name = "Number")
    private String number;

    @Column(name = "Local")
    private String local;

    @Column(name = "City")
    private String city;

    @Column(name = "ZipCode")
    private String zipCode;

    /**
     * Gets the class type of the customer.
     *
     * @return The class type of the customer.
     */
    public String getClassType() {
        return classType;
    }

    /**
     * Sets the class type of the customer.
     *
     * @param classType The class type to set.
     */
    public void setClassType(String classType) {
        this.classType = classType;
    }

    /**
     * Gets the ID of the customer.
     *
     * @return The ID of the customer.
     */
    public Integer getCustomerID() {
        return customerID;
    }

    /**
     * Sets the ID of the customer.
     *
     * @param customerID The ID to set.
     */
    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    /**
     * Gets the first name of the customer.
     *
     * @return The first name of the customer.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the customer.
     *
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the customer.
     *
     * @return The last name of the customer.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the customer.
     *
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the phone number of the customer.
     *
     * @return The phone number of the customer.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param phone The phone number to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the email of the customer.
     *
     * @return The email of the customer.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the customer.
     *
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the address of the customer.
     *
     * @return The address of the customer.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the customer.
     *
     * @param address The address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the number of the customer.
     *
     * @return The number of the customer.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the number of the customer.
     *
     * @param number The number to set.
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Gets the local of the customer.
     *
     * @return The local of the customer.
     */
    public String getLocal() {
        return local;
    }

    /**
     * Sets the local of the customer.
     *
     * @param local The local to set.
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * Gets the city of the customer.
     *
     * @return The city of the customer.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the customer.
     *
     * @param city The city to set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the zip code of the customer.
     *
     * @return The zip code of the customer.
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the zip code of the customer.
     *
     * @param zipCode The zip code to set.
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Returns a string representation of the customer.
     *
     * @return A string representation of the customer.
     */
    @Override
    public String toString() {
        return "Customer{" +
                "classType='" + classType + '\'' +
                ", customerID=" + customerID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", number='" + number + '\'' +
                ", local='" + local + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
