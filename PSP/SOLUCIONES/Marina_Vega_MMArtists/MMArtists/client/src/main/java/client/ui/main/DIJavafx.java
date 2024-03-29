package client.ui.main;

import client.ui.screens.common.UIConstants;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.util.AnnotationLiteral;
import javafx.application.Application;
import javafx.stage.Stage;

public class DIJavafx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        primaryStage.setMinWidth(UIConstants.EIGHT_HUNDRED);
        primaryStage.setMinHeight(UIConstants.SIX_HUNDRED);
        primaryStage.setResizable(true);
        container.getBeanManager()
                .getEvent()
                .select(new AnnotationLiteral<StartupScene>() {
                })
                .fire(primaryStage);
    }
}