module client {
    requires lombok;
    requires jakarta.inject;
    requires okhttp3;
    requires jakarta.cdi;
    requires domain;
    requires io.vavr;
    requires com.google.gson;
    requires retrofit2;
    requires retrofit2.converter.gson;
    requires io.reactivex.rxjava3;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires org.apache.logging.log4j;
    requires retrofit2.adapter.rxjava3;

    opens client.ui.main;
    opens client.common;
    opens client.dao.common;
    opens client.common.config;
    opens client.ui.screens.common;

    opens client.ui.screens.principal to javafx.fxml;
    opens client.ui.screens.login to javafx.fxml;
    opens client.ui.screens.albums_main to javafx.fxml;
    opens client.ui.screens.artists_main to javafx.fxml;
    opens client.ui.screens.songs_main to javafx.fxml;
    opens client.ui.screens.album_detail to javafx.fxml;

    exports client.ui.screens.common;
    exports client.domain.usecases.artists;
    exports client.domain.usecases.albums;
    exports client.domain.usecases.songs;
    exports client.ui.screens.songs_main;
    exports client.ui.screens.albums_main;
    exports client.ui.screens.artists_main;
    exports client.ui.screens.album_detail;
    exports client.dao.impl;
    exports client.ui.screens.login;
    exports client.ui.screens.principal;
    exports client.dao.retrofit;
    exports client.dao.retrofit.llamadas;
    exports client.domain.usecases.login;
    exports client.common.config;
    exports client.dao;
}