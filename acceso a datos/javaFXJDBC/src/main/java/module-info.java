module JavaFXDataAcess {


    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.apache.logging.log4j;
    requires jakarta.inject;
    requires jakarta.cdi;
    requires io.vavr;
    requires java.sql;
    requires com.zaxxer.hikari;

    exports ui.main to javafx.graphics;
    exports ui.screens.principal;
    exports ui.screens.common;
    exports ui.screens.login to javafx.fxml;
    exports dao.impl;
    exports model;
    exports ui.screens.welcome;
    exports ui.screens.order.common;
    exports service;
    exports ui.screens.customer.common;
    exports ui.screens.order;
    exports dao;
    exports ui.screens.customer;
    exports dao.db;
    exports config;

    opens ui.screens.order.common;
    opens ui.screens.principal;
    opens ui.main;
    opens fxml;
    opens ui.screens.login;
    opens ui.screens.common;
    opens service;
    opens ui.screens.welcome;
    opens ui.screens.customer.common;
    opens config;
    opens ui.screens.customer;
    opens ui.screens.order;
    opens dao.utils;
    exports dao.utils;
    opens service.utils;

}
