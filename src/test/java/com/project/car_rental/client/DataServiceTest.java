package com.project.car_rental.client;

import com.project.car_rental.client.services.DataService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.collections.ObservableList;
import java.util.Arrays;

class DataServiceTest {

    @Test
    void getObservableList() {
        // Define a JSON string
        String json = "[\"item1\", \"item2\", \"item3\"]";

        // Call the method with the JSON string and the type
        ObservableList<String> list = DataService.getObservableList(json, String.class);

        // Check the contents of the returned list
        assertEquals(Arrays.asList("item1", "item2", "item3"), list);
    }
}
