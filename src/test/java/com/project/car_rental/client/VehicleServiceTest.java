package com.project.car_rental.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.project.car_rental.client.services.VehicleService;

class VehicleServiceTest {

    @Test
    void validateVehiclesData() {
        VehicleService service = new VehicleService();

        // Test with valid data
        assertTrue(service.validateVehiclesData("Brand", "Model", "Class", "2022", "Manual", "100", "10.0", "Petrol", "100.0"));
    }
}