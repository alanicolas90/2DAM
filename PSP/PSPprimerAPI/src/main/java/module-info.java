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
    requires org.yaml.snakeyaml;


    exports ui.main to javafx.graphics;
    exports ui.pantallas.principal;
    exports ui.pantallas.pantallaNueva;
    exports ui.pantallas.pantalla1;
    exports ui.pantallas.login;
    exports ui.pantallas.listado;
    exports common.config;
    exports ui.pantallas.common;
    exports common;
    exports dao.impl;
    exports domain.usecases;
    exports domain.modelo;


    opens ui.pantallas.pantalla1 to javafx.fxml;
    opens ui.pantallas.pantallaNueva to javafx.fxml;
    opens ui.pantallas.listado to javafx.fxml;
    opens ui.pantallas.login to javafx.fxml;

    opens domain.modelo to javafx.base;
    opens ui.pantallas.principal;
    opens ui.main;
    opens config;
    opens fxml;
    opens domain.modelo.jokes;
    opens domain.modelo.drinks;
    opens ui.pantallas.common;


}
