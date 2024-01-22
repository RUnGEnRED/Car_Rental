package com.project.car_rental.server;

import com.project.car_rental.server.client.connection.ConnectionHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.logging.Logger;

/**
 * Main class for the server in the Car Rental application.
 */
public class ServerMain {
    private static final Logger logger = Logger.getLogger(String.valueOf(ServerMain.class));

    public ServerSocket serverSocket;

    /**
     * Constructor for the ServerMain class.
     *
     * @throws IOException If there is an error creating the ServerSocket.
     */
    public ServerMain() throws IOException {
        serverSocket = new ServerSocket(1234);
    }

    /**
     * Starts the server and handles incoming client connections.
     */
    public void startServer() {
        try {
            System.out.println("Server has started");
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("New request from client (Socket)");
                ConnectionHandler clientConnectionHandler = new ConnectionHandler(socket);

                Thread thread = new Thread(clientConnectionHandler);
                thread.start();

                while (true) {
                    if (!thread.isAlive()) {
                        logger.info("Thread has finished execution");
                        System.out.println("Thread has finished execution");
                        break;
                    }
                }
            }
            closeServerSocket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Closes the server socket.
     */
    public void closeServerSocket() {
        try {
            if(serverSocket != null) {
                serverSocket.close();
            }
            System.out.println("Server has closed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The main method for the ServerMain class.
     *
     * @param args The command line arguments.
     * @throws IOException If there is an error starting the server.
     */
    public static void main(String[] args) throws IOException {
        ServerMain server = new ServerMain();
        server.startServer();
    }
}
