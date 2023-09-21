module JavaFXDataAcess {


    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires lombok;
    requires org.apache.logging.log4j;

    requires jakarta.inject;
    requires jakarta.cdi;
    requires io.vavr;


    exports ui.main to javafx.graphics;
    exports ui.pantallas.principal;
    exports ui.pantallas.common;
    exports ui.pantallas.login to javafx.fxml;
    exports dao.impl;
    exports ui.pantallas.customer.add;
    exports ui.pantallas.customer.remove;
    exports ui.pantallas.customer.update;
    exports ui.pantallas.customer.list;
    exports model;
    exports ui.pantallas.bienvenida;
    exports ui.pantallas.order.update;
    exports ui.pantallas.order.add;
    exports ui.pantallas.order.list;
    exports ui.pantallas.order.remove;

    opens ui.pantallas.order.list;
    opens ui.pantallas.order.add;
    opens ui.pantallas.order.update;
    opens ui.pantallas.order.remove;
    opens ui.pantallas.customer.list;
    opens ui.pantallas.customer.update;
    opens ui.pantallas.customer.remove;
    opens ui.pantallas.principal;
    opens ui.main;
    opens fxml;
    opens ui.pantallas.login;
    opens ui.pantallas.common;
    opens service;
    opens ui.pantallas.customer.add;
    opens ui.pantallas.bienvenida;
    opens ui.pantallas.customer.common;


}
