package com.project.car_rental.client.server.services;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

/**
 * This class provides services related to data processing.
 */
public class DataProcessor {

    private static final Logger logger = Logger.getLogger(DataProcessor.class);
    private String command;
    private String content;
    private String sep_symbol = "###";

    /**
     * This method splits a string into parts based on the separator symbol.
     *
     * @param message The string to be split.
     */
    public void splitStringToParts(String message) {
        try {
            String[] parts = message.split(sep_symbol);

            command = parts[0];
            content = parts[1];
        } catch (Exception e) {
            logger.error("splitStringToParts catch exception");
            throw new RuntimeException(e);
        }
    }

    /**
     * This method connects parts of a string using the separator symbol.
     *
     * @param command The command part of the string.
     * @param content The content part of the string.
     * @return The connected string.
     */
    public String connectPartsToString(String command, String content) {
        return command + sep_symbol + content;
    }

    /**
     * This method deserializes a string into an object of a specified type.
     *
     * @param content The string to be deserialized.
     * @param classOfT The class of the object to be deserialized.
     * @return The deserialized object.
     */
    public <T> T deserializeStringToObject(String content, Class<T> classOfT) {
        return new Gson().fromJson(content, classOfT);
    }

    /**
     * This method serializes an object into a string.
     *
     * @param object The object to be serialized.
     * @return The serialized string.
     */
    public <T> String serializeObjectToString(T object) {
        return new Gson().toJson(object);
    }

    // Getters and setters

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
