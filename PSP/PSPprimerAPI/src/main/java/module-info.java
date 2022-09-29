module javafx.multipantalla {


    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    requires lombok;
    requires org.apache.logging.log4j;
    requires jakarta.inject;
    requires jakarta.cdi;
    requires moshi;
    requires retrofit2;
    requires retrofit2.converter.moshi;
    requires okhttp3;
    requires io.vavr;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires org.yaml.snakeyaml;
    requires java.logging;


    exports ui.main to javafx.graphics;
    exports ui.pantallas.principal;
    exports ui.pantallas.random_drink;
    exports ui.pantallas.pantalla1;
    exports ui.pantallas.login;
    exports ui.pantallas.listado;
    exports common.config;
    exports ui.pantallas.common;
    exports common;
    exports dao.impl;
    exports domain.usecases;
    exports domain.modelo;
    exports dao;
    exports domain.modelo.drinks.alcohol;
    exports domain.modelo.drinks.ingrediente;
    exports domain.modelo.drinks.drink;
    exports domain.modelo.drinks.category;
    exports domain.modelo.drinks.glass;
    exports domain.usecases.impl;


    opens ui.pantallas.pantalla1 to javafx.fxml;
    opens ui.pantallas.random_drink to javafx.fxml;
    opens ui.pantallas.listado to javafx.fxml;
    opens ui.pantallas.login to javafx.fxml;
    opens common;
    opens domain.modelo to javafx.base;
    opens ui.pantallas.principal;
    opens ui.main;
    opens config;
    opens fxml;
    opens ui.pantallas.common;
    opens dao;
    opens domain.modelo.drinks.category;
    opens domain.modelo.drinks.drink;
    opens domain.modelo.drinks.glass;
    opens domain.modelo.drinks.ingrediente;
    opens domain.modelo.drinks.alcohol;
    opens common.config;
}
