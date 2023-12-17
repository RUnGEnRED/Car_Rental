package com.project.car_rental.client.server_connection;

import java.io.*;
import java.net.Socket;

public class ServerConnectionHandler {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public ServerConnectionHandler() {
        try {
            this.socket = new Socket("127.0.0.1", 1234);
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
                System.out.println("Message from server received");
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
                System.out.println("Message to server sent");
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

    // TODO: CREATE CLASS TO INIT CONNECTION - NOT MAIN
/*    public static void main(String[] args) throws IOException {
        ServerConnectionHandler client = new ServerConnectionHandler();
        Customer customer = new Customer();
        customer.setFirstName("John");
        client.sendMessage("hello###imbad");
        client.listenForMessage();
    }*/
}
