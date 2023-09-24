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
    exports ui.screens.principal;
    exports ui.screens.common;
    exports ui.screens.login to javafx.fxml;
    exports dao.impl;
    exports ui.screens.customer.add;
    exports ui.screens.customer.remove;
    exports ui.screens.customer.update;
    exports ui.screens.customer.list;
    exports model;
    exports ui.screens.welcome;
    exports ui.screens.order.update;
    exports ui.screens.order.add;
    exports ui.screens.order.list;
    exports ui.screens.order.remove;
    exports ui.screens.order.common;

    opens ui.screens.order.common;
    opens ui.screens.order.list;
    opens ui.screens.order.add;
    opens ui.screens.order.update;
    opens ui.screens.order.remove;
    opens ui.screens.customer.list;
    opens ui.screens.customer.update;
    opens ui.screens.customer.remove;
    opens ui.screens.principal;
    opens ui.main;
    opens fxml;
    opens ui.screens.login;
    opens ui.screens.common;
    opens service;
    opens ui.screens.customer.add;
    opens ui.screens.welcome;
    opens ui.screens.customer.common;


}
