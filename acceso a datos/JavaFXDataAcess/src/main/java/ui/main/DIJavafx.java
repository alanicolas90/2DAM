package ui.main;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.util.AnnotationLiteral;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DIJavafx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            SeContainerInitializer initializer = SeContainerInitializer.newInstance();
            final SeContainer container = initializer.initialize();
            primaryStage.setResizable(true);
            container.getBeanManager().getEvent().select(new AnnotationLiteral<StartupScene>() {
            }).fire(primaryStage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
