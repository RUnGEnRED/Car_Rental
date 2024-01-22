module com.project.car_rental {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core; // Line Added
    requires jakarta.persistence; // Line Added
    requires com.google.gson; // Line Added

    requires java.naming; // Line Added
    requires reload4j; // Line Added
    requires tornadofx.controls; // Line Added


    // CLIENT
    exports com.project.car_rental.client; // Line Added
    opens com.project.car_rental.client to javafx.fxml, org.hibernate.orm.core; // Line Added

    // Controllers (CLIENT)
    exports com.project.car_rental.client.controllers; // Line Added
    opens com.project.car_rental.client.controllers to javafx.fxml, org.hibernate.orm.core; // Line Added

    // Server connection (CLIENT)
    exports com.project.car_rental.client.server.connection; // Line Added
    opens com.project.car_rental.client.server.connection to javafx.fxml, org.hibernate.orm.core; // Line Added

    // Server services (CLIENT)
    exports com.project.car_rental.client.server.services;
    opens com.project.car_rental.client.server.services to javafx.fxml, org.hibernate.orm.core;

    // DB models (CLIENT)
    exports com.project.car_rental.client.db.models; // Line Added
    opens com.project.car_rental.client.db.models to javafx.fxml, org.hibernate.orm.core, com.google.gson; // Line Added

    // Services (CLIENT)
    exports com.project.car_rental.client.services; // Line Added
    opens com.project.car_rental.client.services to javafx.fxml, org.hibernate.orm.core; // Line Added

    // SERVER
    exports com.project.car_rental.server; // Line Added
    opens com.project.car_rental.server to javafx.fxml, org.hibernate.orm.core; // Line Added

    // Client connection (SERVER)
    exports com.project.car_rental.server.client.connection; // Line Added
    opens com.project.car_rental.server.client.connection to javafx.fxml, org.hibernate.orm.core; // Line Added

    // DB models (SERVER)
    exports com.project.car_rental.server.db.models; // Line Added
    opens com.project.car_rental.server.db.models to javafx.fxml, org.hibernate.orm.core, com.google.gson; // Line Added

    // Services (SERVER)
    exports com.project.car_rental.server.client.services; // Line Added
    opens com.project.car_rental.server.client.services to javafx.fxml, org.hibernate.orm.core; // Line Added
}