module com.example.pms {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.pms to javafx.fxml;
    exports com.example.pms;
    exports com.example.pms.entities;
    opens com.example.pms.entities to javafx.fxml;
    exports com.example.pms.services;
    opens com.example.pms.services to javafx.fxml;
    exports com.example.pms.controllers;
    opens com.example.pms.controllers to javafx.fxml;
}