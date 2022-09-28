package ui.main;

import dao.DaoDrinks;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;


public class Main {

    public static void main(String[] args) {

        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();

        DaoDrinks daoDrinks = container.select(DaoDrinks.class).get();


        System.out.println(daoDrinks.getIngredients());

    }
}
