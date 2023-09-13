module plantillaJavaFX {


    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    requires lombok;

    requires jakarta.inject;
    requires jakarta.cdi;
    requires retrofit2;
    requires retrofit2.converter.moshi;
    requires io.vavr;
    requires com.squareup.moshi;
    requires okhttp3;
    requires io.reactivex.rxjava3;
    requires org.pdfsam.rxjavafx;


    //    requires rxjavafx;
//    requires org.pdfsam.rxjavafx;
    requires com.google.gson;
    requires retrofit2.adapter.rxjava3;


    exports ui.main to javafx.graphics;
    exports ui.pantallas.principal;
    exports ui.pantallas.common;
    exports domain.modelo;



//    opens ui.pantallas.principal to javafx.fxml;

    opens domain.modelo to javafx.base;

    opens ui.pantallas.principal;
    opens ui.main;
    opens fxml;

//    exports ui;
//    opens domain.modelo to javafx.base;
//    exports ui.pantallas.principal;
//
//    opens ui.pantallas.principal to javafx.fxml;

}
