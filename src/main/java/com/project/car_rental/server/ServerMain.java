package com.project.car_rental.server;

import com.project.car_rental.server.client_connection.ClientConnectionHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public ServerSocket serverSocket;

    public ServerMain() throws IOException {
        serverSocket = new ServerSocket(1234);
    }

    public void startServer() {
        try {
            System.out.println("Server has started");
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("New request from client (Socket)");
                ClientConnectionHandler clientConnectionHandler = new ClientConnectionHandler(socket);

                Thread thread = new Thread(clientConnectionHandler);
                thread.start();

                while (true) {
                    if (!thread.isAlive()) {
                        System.out.println("Thread has finished execution");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    public static void main(String[] args) throws IOException {
        ServerMain server = new ServerMain();
        server.startServer();
    }
}