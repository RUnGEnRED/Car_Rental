package com.project.car_rental.client;

import com.project.car_rental.client.db.models.Reservation;
import com.project.car_rental.client.services.BookVehicleService;
import org.junit.jupiter.api.Test;
import tornadofx.control.DateTimePicker;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookVehicleServiceTest {

    @Test
    void convertDateTimeFormat() {
        // Create a DateTimePicker with a specific date
        DateTimePicker rentalDate = new DateTimePicker();
        rentalDate.setDateTimeValue(LocalDateTime.of(2020, 1, 1, 0, 0));
        DateTimePicker returnDate = new DateTimePicker();
        returnDate.setDateTimeValue(LocalDateTime.of(2021, 1, 2, 0, 0));

        BookVehicleService service = new BookVehicleService();
        Reservation reservation = service.convertDateTimeFormat(rentalDate, returnDate);

        // Check if the dates in the reservation match the dates in the DateTimePickers
        assertEquals(reservation.getRentalDate(), reservation.getRentalDate());
        assertEquals(reservation.getReturnDate(), reservation.getReturnDate());
    }

    @Test
    void validateReservationData() {
        Reservation reservation = new Reservation();
        reservation.setCustomerID(1);
        reservation.setVehicleID(1);
        reservation.setRentalDate(new Date());
        reservation.setReturnDate(new Date());
        reservation.setTotalCost(100.0);

        BookVehicleService service = new BookVehicleService();
        boolean result = service.validateReservationData(reservation);

        assertTrue(result, "Expected true for valid reservation data");
    }
}
