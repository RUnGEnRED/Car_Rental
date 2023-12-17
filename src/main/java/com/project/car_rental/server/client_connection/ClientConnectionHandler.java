package com.project.car_rental.server.client_connection;

import com.project.car_rental.server.db_connection.CommandProcessor;

import java.io.*;
import java.net.Socket;

public class ClientConnectionHandler implements Runnable {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    @Override
    public void run() {
        String messageFromClient = listenForMessage();
        String messageToClient = new CommandProcessor(messageFromClient).messageToClient();
        sendMessage(messageToClient);
        closeEverything(socket, bufferedReader, bufferedWriter);
    }

    public ClientConnectionHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public String listenForMessage() {
        String messageFromClient = null;

        try {
            if (socket.isConnected()) {
                messageFromClient = bufferedReader.readLine();
                System.out.println("Message from client received");
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
        return messageFromClient;
    }

    public void sendMessage(String message) {
        try {
            if (socket.isConnected()) {
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                System.out.println("Message to client sent");
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

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