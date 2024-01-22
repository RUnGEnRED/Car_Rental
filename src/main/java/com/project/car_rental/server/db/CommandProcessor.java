package com.project.car_rental.server.db;

import com.project.car_rental.server.db.models.Customer;
import com.project.car_rental.server.client.services.DataProcessor;
import com.project.car_rental.server.db.connection.*;
import com.project.car_rental.server.db.models.Reservation;
import com.project.car_rental.server.db.models.Vehicle;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * This class processes commands from the client.
 */
public class CommandProcessor {

    private static final Logger logger = Logger.getLogger(CommandProcessor.class);
    private String messageFromClient;
    private String messageToClient;

    /**
     * Constructs a new CommandProcessor with the specified message from the client.
     *
     * @param messageFromClient The message from the client.
     */
    public CommandProcessor(String messageFromClient) {
        this.messageFromClient = messageFromClient;
    }

    /**
     * Processes the message from the client and returns a response.
     *
     * @return The response to the client.
     */
    public String messageToClient() {
        Customer customer;
        Vehicle vehicle;
        Reservation reservation;

        List dbTable;
        String serializeObject;

        DataProcessor dataProcessor = new DataProcessor();
        dataProcessor.splitStringToParts(messageFromClient);
        messageToClient = "Command from client is in wrong format";

        switch (dataProcessor.getCommand()) {
            case "readCustomerList":
                dbTable = new CustomerConnection().readCustomerList();
                serializeObject = dataProcessor.serializeObjectToString(dbTable);
                messageToClient = dataProcessor.connectPartsToString("customerList", serializeObject);
                return messageToClient;

            case "createCustomer":
                customer = dataProcessor.deserializeStringToObject(dataProcessor.getContent(), Customer.class);
                new CustomerConnection().createCustomer(customer);
                serializeObject = dataProcessor.serializeObjectToString(new Object());
                messageToClient = dataProcessor.connectPartsToString("customerCreated", serializeObject);
                return messageToClient;

            case "updateCustomer":
                customer = dataProcessor.deserializeStringToObject(dataProcessor.getContent(), Customer.class);
                new CustomerConnection().updateCustomer(customer);
                serializeObject = dataProcessor.serializeObjectToString(new Object());
                messageToClient = dataProcessor.connectPartsToString("customerUpdated", serializeObject);
                return messageToClient;

            case "deleteCustomer":
                customer = dataProcessor.deserializeStringToObject(dataProcessor.getContent(), Customer.class);
                new CustomerConnection().deleteCustomer(customer);
                serializeObject = dataProcessor.serializeObjectToString(new Object());
                messageToClient = dataProcessor.connectPartsToString("customerDeleted", serializeObject);
                return messageToClient;

            // --------------------

            case "readVehicleList":
                dbTable = new VehicleConnection().readVehicleList();
                serializeObject = dataProcessor.serializeObjectToString(dbTable);
                messageToClient = dataProcessor.connectPartsToString("vehicleList", serializeObject);
                return messageToClient;

            case "readVehicleListOnDateFrame":
                reservation = dataProcessor.deserializeStringToObject(dataProcessor.getContent(), Reservation.class);
                dbTable = new VehicleConnection().readVehicleListOnDateFrame(reservation.getRentalDate(), reservation.getReturnDate());
                serializeObject = dataProcessor.serializeObjectToString(dbTable);
                messageToClient = dataProcessor.connectPartsToString("vehicleListOnDateFrame", serializeObject);
                return messageToClient;

            case "createVehicle":
                vehicle = dataProcessor.deserializeStringToObject(dataProcessor.getContent(), Vehicle.class);
                new VehicleConnection().createVehicle(vehicle);
                serializeObject = dataProcessor.serializeObjectToString(new Object());
                messageToClient = dataProcessor.connectPartsToString("vehicleCreated", serializeObject);
                return messageToClient;

            case "updateVehicle":
                vehicle = dataProcessor.deserializeStringToObject(dataProcessor.getContent(), Vehicle.class);
                new VehicleConnection().updateVehicle(vehicle);
                serializeObject = dataProcessor.serializeObjectToString(new Object());
                messageToClient = dataProcessor.connectPartsToString("vehicleUpdated", serializeObject);
                return messageToClient;

            case "deleteVehicle":
                vehicle = dataProcessor.deserializeStringToObject(dataProcessor.getContent(), Vehicle.class);
                new VehicleConnection().deleteVehicle(vehicle);
                serializeObject = dataProcessor.serializeObjectToString(new Object());
                messageToClient = dataProcessor.connectPartsToString("vehicleDeleted", serializeObject);
                return messageToClient;

            // --------------------

            case "readReservationList":
                dbTable = new ReservationConnection().readReservationList();
                serializeObject = dataProcessor.serializeObjectToString(dbTable);
                messageToClient = dataProcessor.connectPartsToString("reservationList", serializeObject);
                return messageToClient;

            case "createReservation":
                reservation = dataProcessor.deserializeStringToObject(dataProcessor.getContent(), Reservation.class);
                new ReservationConnection().createReservation(reservation);
                serializeObject = dataProcessor.serializeObjectToString(new Object());
                messageToClient = dataProcessor.connectPartsToString("reservationCreated", serializeObject);
                return messageToClient;

            case "updateReservation":
                reservation = dataProcessor.deserializeStringToObject(dataProcessor.getContent(), Reservation.class);
                new ReservationConnection().updateReservation(reservation);
                serializeObject = dataProcessor.serializeObjectToString(new Object());
                messageToClient = dataProcessor.connectPartsToString("reservationUpdated", serializeObject);
                return messageToClient;

            case "deleteReservation":
                reservation = dataProcessor.deserializeStringToObject(dataProcessor.getContent(), Reservation.class);
                new ReservationConnection().deleteReservation(reservation);
                serializeObject = dataProcessor.serializeObjectToString(new Object());
                messageToClient = dataProcessor.connectPartsToString("reservationDeleted", serializeObject);
                return messageToClient;

            // --------------------

            default:
                logger.error("Command from client is in wrong format");
                break;
        }

        return messageToClient;
    }
}
