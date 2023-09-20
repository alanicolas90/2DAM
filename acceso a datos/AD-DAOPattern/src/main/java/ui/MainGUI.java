package ui;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import model.Coffee;
import services.CoffeeService;

import java.util.List;


public class MainGUI {

    // This code implements the GUI layer
    public static void main(String[] args) {

        //Code por dependency injection
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        
        //Use @Container to be able to call the interface
        CoffeeService sc = container.select(CoffeeService.class).get();

        System.out.println("List of coffees:");
        sc.getAll().forEach(System.out::println);

        }
		
}
