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
    exports common.config;
    exports dao.impl;
    exports domain.service;
    exports dao;
    exports domain.modelo.pokemon;
    exports ui.screens.tablepokemon;
    exports ui.screens.tableberrie;


    opens ui.screens.principal;
    opens ui.main;
    opens fxml;
    opens ui.screens.common;
    opens dao;
    opens common.config;
    opens domain.modelo.pokemon;
    opens ui.screens.tablepokemon;
    exports domain.modelo.berry;
    opens domain.modelo.berry;
    opens ui.screens.tableberrie;
    opens common.constantes;
    exports dao.retrofit;
    opens dao.retrofit;


}
