package com.project.car_rental.server.db_connection;

import com.project.car_rental.server.services.DataProcessor;

import java.util.List;

public class CommandProcessor {

    private String messageFromClient;
    private String messageToClient;

    public CommandProcessor(String messageFromClient) {
        this.messageFromClient = messageFromClient;
    }

    public String messageToClient() {
        DataProcessor dataProcessor = new DataProcessor();
        dataProcessor.splitStringToParts(messageFromClient);
        messageToClient = "Command from client is in wrong format";

        switch (dataProcessor.getCommand()) {
            case "getCustomerTable":
                List dbTable = new CustomerConnection().getCustomerTable();
                String serializeObject = dataProcessor.serializeObjectToString(dbTable);
                messageToClient = dataProcessor.connectPartsToString("CustomerTableAsList", serializeObject);
                return messageToClient;

            case "getReservationTable":
            // ...

            default:
                System.err.println("Command from client is in wrong format");
                break;
        }

        return messageToClient;
    }
}
