package com.project.car_rental.client;

import com.project.car_rental.client.server.services.DataProcessor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataProcessorTest {

    @Test
    void splitStringToParts() {
        DataProcessor dataProcessor = new DataProcessor();
        dataProcessor.splitStringToParts("command###content");

        assertEquals("command", dataProcessor.getCommand());
        assertEquals("content", dataProcessor.getContent());
    }

    @Test
    void connectPartsToString() {
        DataProcessor dataProcessor = new DataProcessor();
        String result = dataProcessor.connectPartsToString("command", "content");

        assertEquals("command###content", result);
    }

    @Test
    void deserializeStringToObject() {
        DataProcessor dataProcessor = new DataProcessor();
        String json = "{\"name\":\"John\"}";

        // Make TestClass a static class
        class TestClass {
            public String name;
        }

        TestClass testObject = dataProcessor.deserializeStringToObject(json, TestClass.class);

        assertEquals("John", testObject.name);
    }


    @Test
    void serializeObjectToString() {
        DataProcessor dataProcessor = new DataProcessor();

        class TestClass {
            public String name = "John";
        }

        TestClass testObject = new TestClass();
        String json = dataProcessor.serializeObjectToString(testObject);

        assertEquals("{\"name\":\"John\"}", json);
    }
}
