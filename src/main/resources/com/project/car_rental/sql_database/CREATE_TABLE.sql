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
    FOREIGN KEY (CustomerID) REFERENCES customer(CustomerID) ON DELETE SET NULL,
    FOREIGN KEY (VehicleID) REFERENCES vehicle(VehicleID) ON DELETE SET NULL
);

DELIMITER //
CREATE PROCEDURE GetReservations()
BEGIN
    SELECT
        COALESCE(r.ReservationID, 0) AS ReservationID,
        COALESCE(c.CustomerID, 0) AS CustomerID,
        COALESCE(CONCAT(c.FirstName, ' ', c.LastName), '0') AS CustomerName,
        COALESCE(r.VehicleID, 0) AS VehicleID,
        COALESCE(CONCAT(v.Brand, ' ', v.Model), '0') AS VehicleName,
        COALESCE(r.RentalDate, '0') AS RentalDate,
        COALESCE(r.ReturnDate, '0') AS ReturnDate,
        COALESCE(r.TotalCost, 0) AS TotalCost
    FROM
        reservation r
    LEFT JOIN
        customer c ON r.CustomerID = c.CustomerID
    LEFT JOIN
        vehicle v ON r.VehicleID = v.VehicleID;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE AvailableVehicles
(IN startDate DATETIME, IN endDate DATETIME)
BEGIN
    SELECT v.*
    FROM vehicle v
    LEFT JOIN (
        SELECT VehicleID
        FROM reservation
        WHERE NOT (ReturnDate < startDate OR RentalDate > endDate)
    ) r ON v.VehicleID = r.VehicleID
    WHERE r.VehicleID IS NULL;
END //
DELIMITER ;
