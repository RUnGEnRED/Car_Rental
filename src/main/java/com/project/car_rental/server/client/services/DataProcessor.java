package com.project.car_rental.server.client.services;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

/**
 * This class processes data for the car rental system.
 */
public class DataProcessor {

    private static final Logger logger = Logger.getLogger(DataProcessor.class);
    private String command;
    private String content;
    private String sep_symbol = "###";

    /**
     * Splits the message into command and content parts.
     *
     * @param message The message to split.
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
     * Connects the command and content parts into a single string.
     *
     * @param command The command part.
     * @param content The content part.
     * @return The connected string.
     */
    public String connectPartsToString(String command, String content) {
        return command + sep_symbol + content;
    }

    /**
     * Deserializes the content into an object of the specified class.
     *
     * @param content The content to deserialize.
     * @param classOfT The class of the object.
     * @param <T> The type of the object.
     * @return The deserialized object.
     */
    public <T> T deserializeStringToObject(String content, Class<T> classOfT) {
        return new Gson().fromJson(content, classOfT);
    }

    /**
     * Serializes the object into a string.
     *
     * @param object The object to serialize.
     * @param <T> The type of the object.
     * @return The serialized string.
     */
    public <T> String serializeObjectToString(T object) {
        return new Gson().toJson(object);
    }

    /**
     * Gets the separator symbol.
     *
     * @return The separator symbol.
     */
    public String getSep_symbol() {
        return sep_symbol;
    }

    /**
     * Sets the separator symbol.
     *
     * @param sep_symbol The separator symbol.
     */
    public void setSep_symbol(String sep_symbol) {
        this.sep_symbol = sep_symbol;
    }

    /**
     * Gets the command.
     *
     * @return The command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Gets the content.
     *
     * @return The content.
     */
    public String getContent() {
        return content;
    }
}
