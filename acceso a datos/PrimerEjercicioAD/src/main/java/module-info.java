module javafx.multipantalla {


    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    requires lombok;
    requires org.apache.logging.log4j;
    requires jakarta.inject;
    requires jakarta.cdi;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;

    exports ui.main to javafx.graphics;
    exports ui.pantallas.principal;
    exports ui.pantallas.pantallaNueva;
    exports ui.pantallas.pantalla1;
    exports ui.pantallas.login;
    exports ui.pantallas.listado;
    exports config;
    exports ui.pantallas.common;
    exports dao.impl;
    exports domain.usecases;
    exports ui.pantallas.newspaper;

    opens ui.pantallas.pantalla1 to javafx.fxml;
    opens ui.pantallas.pantallaNueva to javafx.fxml;
    opens ui.pantallas.listado to javafx.fxml;
    opens ui.pantallas.login to  javafx.fxml;

    opens domain.modelo to javafx.base;
    opens ui.pantallas.principal;
    opens ui.main;
    opens css;
    opens fxml;
    opens config;


}
