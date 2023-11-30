module mi_primer_API_rest {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    requires lombok;
    requires org.apache.logging.log4j;


    requires jakarta.inject;
    requires jakarta.cdi;
    requires retrofit2;
    requires retrofit2.converter.moshi;
    requires io.vavr;
    requires com.squareup.moshi;
    requires okhttp3;
    requires io.reactivex.rxjava3;
    requires org.pdfsam.rxjavafx;

    requires retrofit2.adapter.rxjava3;

    exports ui.main to javafx.graphics;
    exports ui.screens.principal;
    exports ui.screens.list_countries;
    exports common.config;
    exports ui.screens.common;
    exports common;
    exports dao.impl;
    exports domain.usecases;
    exports domain.modelo;
    exports dao.retrofit;
    exports ui.screens.welcome;
    exports dao.retrofit.llamadas;
    exports dao.retrofit.modelo;
    exports ui.screens.states_by_country;
    exports ui.screens.cities_by_country;

    opens ui.screens.list_countries to javafx.fxml;
    opens ui.screens.cities_by_country to javafx.fxml;

    opens domain.modelo to javafx.base;

    opens dao.retrofit.modelo;
    opens ui.screens.principal;
    opens ui.main;
    opens config;
    opens fxml;
    opens common;
    opens common.config;
    exports dao;
}