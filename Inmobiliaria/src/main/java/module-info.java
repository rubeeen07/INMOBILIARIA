module ies.dam.inmobiliaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens ies.dam.inmobiliaria to javafx.fxml;
    opens ies.dam.inmobiliaria.controller to javafx.fxml;
    opens ies.dam.inmobiliaria.model to javafx.base;

    exports ies.dam.inmobiliaria;
    exports ies.dam.inmobiliaria.controller;
    exports ies.dam.inmobiliaria.model;
}
