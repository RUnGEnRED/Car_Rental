INSERT INTO customer (FirstName, LastName, Phone, Email, Address, Number, Local, City, ZipCode)
VALUES
('John', 'Doe', '+48 123 456 789', 'john.doe@gmail.com', 'Main Street', '1a', '0', 'Warsaw', '00-001'),
('Jane', 'Smith', '+1 555 5555', 'jane.smith@yahoo.com', 'Second Avenue', '2b', '0', 'Berlin', '22022'),
('John', 'Travolta', '+1 123 3121', 'j.travol.king@outlook.com', 'Third Road', '3c', '', 'New York', '202020');

INSERT INTO vehicle (Brand, Model, VehicleClass, YearOfProduction, Transmission, Horsepower, FuelConsumption, FuelType, PricePerDay)
VALUES
('Toyota', 'Corolla', 'Compact', 2020, 'Automatic', 139, 7.5, 'Petrol', 50.00),
('Tesla', 'Model 3', 'Electric', 2021, 'Automatic', 283, 0.0, 'Electric', 70.00),
('Ford', 'Focus', 'Compact', 2019, 'Manual', 160, 8.0, 'Petrol', 45.00);

INSERT INTO reservation (CustomerID, VehicleID, RentalDate, ReturnDate, TotalCost)
VALUES
(1, 1, '2024-01-01 10:00:00', '2024-01-10 13:00:00', 500.00),
(2, 2, '2024-02-01 10:00:00', '2024-02-10 15:00:00', 700.00),
(3, 2, '2024-03-01 05:00:00', '2024-03-10 10:30:00', 450.00);
