package com.project.car_rental.server.client.connection;

import com.project.car_rental.server.db.CommandProcessor;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

/**
 * Class for handling client connections in the Car Rental application.
 */
public class ConnectionHandler implements Runnable {

    private static final Logger logger = Logger.getLogger(ConnectionHandler.class);
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    /**
     * The run method for the Runnable interface.
     * Listens for a message from the client, processes it, and sends a response.
     */
    @Override
    public void run() {
        String messageFromClient = listenForMessage();
        String messageToClient = new CommandProcessor(messageFromClient).messageToClient();
        sendMessage(messageToClient);
        closeEverything(socket, bufferedReader, bufferedWriter);
    }

    /**
     * Constructor for the ConnectionHandler class.
     *
     * @param socket The socket for the client connection.
     */
    public ConnectionHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            logger.error("ConnectionHandler catch exception");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Listens for a message from the client.
     *
     * @return The message from the client.
     */
    public String listenForMessage() {
        String messageFromClient = null;

        try {
            if (socket.isConnected()) {
                messageFromClient = bufferedReader.readLine();
                System.out.println("Message from client received");
            }
        } catch (IOException e) {
            logger.error("listenForMessage catch exception");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
        return messageFromClient;
    }

    /**
     * Sends a message to the client.
     *
     * @param message The message to send.
     */
    public void sendMessage(String message) {
        try {
            if (socket.isConnected()) {
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                System.out.println("Message to client sent");
            }
        } catch (IOException e) {
            logger.error("sendMessage catch exception");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Closes the socket and the input and output streams.
     *
     * @param socket The socket to close.
     * @param bufferedReader The input stream to close.
     * @param bufferedWriter The output stream to close.
     */
    public void closeEverything (Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (socket != null) {
                socket.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
