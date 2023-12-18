-- ADD EXAMPLE customer
INSERT INTO customer (FirstName, LastName, Phone, Email, Address, Number, Local, City, ZipCode)
VALUES 
('John', 'Doe', '1234567890', 'john.doe@example.com', '123 Main St', '1', 'Local1', 'City1', '12345'),
('Jane', 'Smith', '0987654321', 'jane.smith@example.com', '456 High St', '2', 'Local2', 'City2', '67890'),
('Robert', 'Johnson', '1122334455', 'robert.johnson@example.com', '789 Low St', '3', 'Local3', 'City3', '11223');

-- ADD EXAMPLE vehicle
INSERT INTO vehicle (Brand, Model, VehicleClass, YearOfProduction, Transmission, Horsepower, FuelConsumption, FuelType, PricePerDay)
VALUES 
('Brand1', 'Model1', 'Class1', 2020, 'Automatic', 120, 7.5, 'Gasoline', 50.00),
('Brand2', 'Model2', 'Class2', 2021, 'Manual', 150, 8.5, 'Diesel', 60.00),
('Brand3', 'Model3', 'Class3', 2022, 'Automatic', 180, 9.5, 'Electric', 70.00);

-- ADD EXAMPLE reservation
INSERT INTO reservation (CustomerID, VehicleID, RentalDate, ReturnDate, TotalCost)
VALUES 
(1, 1, '2023-12-01 10:00:00', '2023-12-10 10:00:00', 500.00),
(2, 2, '2023-12-05 12:00:00', '2023-12-15 12:00:00', 600.00),
(3, 3, '2023-12-10 14:00:00', '2023-12-20 14:00:00', 700.00);
