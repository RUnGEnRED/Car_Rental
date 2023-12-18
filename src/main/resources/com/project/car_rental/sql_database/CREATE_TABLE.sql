CREATE DATABASE car_rental;

USE car_rental;

CREATE TABLE customer (
    CustomerID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(100),
    LastName VARCHAR(100),
    Phone VARCHAR(15),
    Email VARCHAR(255),
    Address VARCHAR(255),
    Number VARCHAR(10),
    Local VARCHAR(100),
    City VARCHAR(100),
    ZipCode VARCHAR(10)
);

CREATE TABLE vehicle (
    VehicleID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Brand VARCHAR(50),
    Model VARCHAR(50),
    VehicleClass VARCHAR(50),
    YearOfProduction YEAR,
    Transmission VARCHAR(50),
    Horsepower INT,
    FuelConsumption DECIMAL(5,2),
    FuelType VARCHAR(50),
    PricePerDay DECIMAL(7,2)
);

CREATE TABLE reservation (
    ReservationID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT,
    VehicleID INT,
    RentalDate DATETIME,
    ReturnDate DATETIME,
    TotalCost DECIMAL(7,2),
    FOREIGN KEY (CustomerID) REFERENCES customer(CustomerID),
    FOREIGN KEY (VehicleID) REFERENCES vehicle(VehicleID)
);
