package dao;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class MainTEST {

    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();

        CustomerDao customerDao = container.select(CustomerDao.class).get();
        System.out.println("List of coffees:");

        customerDao.getAll();

    }
}
