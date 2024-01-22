package com.project.car_rental.client;

import com.project.car_rental.client.services.CustomersService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomersServiceTest {

    @Test
    void validateCustomerData() {
        CustomersService service = new CustomersService();

        assertTrue(service.validateCustomerData("John", "Doe", "+123456789", "john.doe@example.com", "Street", "1a", "1", "City", "12345"));
    }
}
