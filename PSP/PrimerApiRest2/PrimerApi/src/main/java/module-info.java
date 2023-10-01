module plantillaJavaFX {


    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires lombok;
    requires org.apache.logging.log4j;

    requires jakarta.inject;
    requires jakarta.cdi;
    requires io.vavr;
    requires retrofit2;
    requires moshi;
    requires okhttp3;
    requires retrofit2.converter.moshi;
    requires java.desktop;


    exports ui.main to javafx.graphics;
    exports ui.screens.principal;
    exports ui.screens.common;
    exports ui.screens.login to javafx.fxml;
    exports common.config;
    exports dao.impl;
    exports domain.service;
    exports dao;
    exports ui.screens.welcome;
    exports domain.modelo.pokemon;


    opens ui.screens.principal;
    opens ui.main;
    opens fxml;
    opens ui.screens.login;
    opens ui.screens.common;
    opens ui.screens.welcome;
    opens dao;
    opens common.config;
    opens domain.modelo.pokemon;


}
