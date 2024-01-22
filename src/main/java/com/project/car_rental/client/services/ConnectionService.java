package com.project.car_rental.client.services;

import com.project.car_rental.client.server.connection.ConnectionHandler;
import com.project.car_rental.client.server.services.DataProcessor;

/**
 * This class provides services related to server connections.
 */
public class ConnectionService {

    /**
     * This method sends data to the server and processes the server's response.
     *
     * @param command The command to be sent to the server.
     * @param object The object to be serialized and sent to the server.
     * @return A DataProcessor object that contains the server's response.
     */
    public <T> DataProcessor sendDataToServer(String command, T object) {
        DataProcessor dataProcessor = new DataProcessor();
        String serializeObject = dataProcessor.serializeObjectToString(object);
        String messageToServer = dataProcessor.connectPartsToString(command, serializeObject);

        ConnectionHandler client = new ConnectionHandler();
        client.sendMessage(messageToServer);
        String messageFromServer = client.listenForMessage();

        dataProcessor.splitStringToParts(messageFromServer);

        return dataProcessor;
    }
}
