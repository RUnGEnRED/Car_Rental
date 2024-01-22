package com.project.car_rental.client.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.lang.reflect.Type;
import java.util.List;

/**
 * DataService is a utility class that provides methods to handle data.
 */
public class DataService {

    /**
     * Converts a JSON string to an ObservableList of a specified type.
     *
     * @param content The JSON string to be converted.
     * @param type The Type of the objects in the list.
     * @param <T> The type of the objects in the list.
     * @return An ObservableList of objects of type T.
     */
    public static <T> ObservableList<T> getObservableList(String content, Type type) {
        Type listType = TypeToken.getParameterized(List.class, type).getType();
        List<T> items = new Gson().fromJson(content, listType);

        return FXCollections.observableArrayList(items);
    }
}
