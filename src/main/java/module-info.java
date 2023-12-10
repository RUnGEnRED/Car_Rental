module com.project.car_rental {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;
    requires org.hibernate.orm.core; // Line Added
    requires jakarta.persistence; // Line Added

    requires java.naming; // Line Added

    // Car_rental
    opens com.project.car_rental to javafx.fxml, org.hibernate.orm.core; // Line extended
    exports com.project.car_rental; // Line extended

    // Controllers
    exports com.project.car_rental.controllers; // Line extended
    opens com.project.car_rental.controllers to javafx.fxml, org.hibernate.orm.core; // Line extended

    // Utilities
    exports com.project.car_rental.utilities; // Line extended
    opens com.project.car_rental.utilities to javafx.fxml, org.hibernate.orm.core; // Line extended

    // Entities
    exports com.project.car_rental.entities; // Line extended
    opens com.project.car_rental.entities to javafx.fxml, org.hibernate.orm.core;
}