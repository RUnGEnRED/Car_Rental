package com.project.car_rental.server.services;

import com.google.gson.Gson;

public class DataProcessor {

    private String command;
    private String content;
    private String sep_symbol = "###";

    public void splitStringToParts(String message) {
        try {
            String[] parts = message.split(sep_symbol);

            command = parts[0];
            content = parts[1];
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String connectPartsToString(String command, String content) {
        return command + sep_symbol + content;
    }

    public <T> T deserializeStringToObject(String content, Class<T> classOfT) {
        return new Gson().fromJson(content, classOfT);
    }

    public <T> String serializeObjectToString(T object) {
        return new Gson().toJson(object);
    }

    public String getSep_symbol() {
        return sep_symbol;
    }

    public void setSep_symbol(String sep_symbol) {
        this.sep_symbol = sep_symbol;
    }

    public String getCommand() {
        return command;
    }

    public String getContent() {
        return content;
    }
}
