package com.project.car_rental.client.server.connection;

import org.apache.log4j.Logger;
import java.io.*;
import java.net.Socket;

/**
 * ConnectionHandler is a class that handles the connection to the server.
 */
public class ConnectionHandler {

    private static final Logger logger = Logger.getLogger(ConnectionHandler.class);
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    /**
     * Constructor for the ConnectionHandler class.
     * It initializes the socket, BufferedReader and BufferedWriter.
     */
    public ConnectionHandler() {
        try {
            this.socket = new Socket("127.0.0.1", 1234);
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            logger.error("ConnectionHandler catch exception");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Listens for a message from the server.
     *
     * @return The message from the server.
     */
    public String listenForMessage() {
        String messageFromClient = null;

        try {
            if (socket.isConnected()) {
                messageFromClient = bufferedReader.readLine();
                System.out.println("Message from server received");
            }
        } catch (IOException e) {
            logger.error("listenForMessage catch exception");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
        return messageFromClient;
    }

    /**
     * Sends a message to the server.
     *
     * @param message The message to be sent.
     */
    public void sendMessage(String message) {
        try {
            if (socket.isConnected()) {
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                System.out.println("Message to server sent");
            }
        } catch (IOException e) {
            logger.error("sendMessage catch exception");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Closes the socket, BufferedReader and BufferedWriter.
     *
     * @param socket The socket to be closed.
     * @param bufferedReader The BufferedReader to be closed.
     * @param bufferedWriter The BufferedWriter to be closed.
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
