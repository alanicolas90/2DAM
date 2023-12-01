package ui.main;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import ui.main.exercises.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();

        Scanner sc = new Scanner(System.in);
        String response;
        System.out.println("Lets try the EXAM");
        System.out.println(ConstantsMain.MENU_SELECTION_EXERCISE_EXAM);

        do {
            System.out.print("Option: ");
            response = sc.nextLine();
            switch (response) {
                case "1":
                    Exercise1 exercise1 = container.select(Exercise1.class).get();
                    exercise1.run(sc);
                    break;
                case "2":
                    Exercise2 exercise2 = container.select(Exercise2.class).get();
                    exercise2.run(sc);
                    break;
                case "3":
                    Exercise3 exercise3 = container.select(Exercise3.class).get();
                    exercise3.run(sc);
                    break;
                case "4":
                    Exercise4 exercise4 = container.select(Exercise4.class).get();
                    exercise4.run(sc);
                    break;
                case "5":
                    Exercise5 exercise5 = container.select(Exercise5.class).get();
                    exercise5.run(sc);
                    break;
                case "6":
                    Exercise6 exercise6 = container.select(Exercise6.class).get();
                    exercise6.run(sc);
                    break;
                case "7":
                    Exercise7 exercise7 = container.select(Exercise7.class).get();
                    exercise7.run(sc);
                    break;
                case "8":
                    Exercise8 exercise8 = container.select(Exercise8.class).get();
                    exercise8.run(sc);
                    break;
                case "9":
                    Exercise9 exercise9 = container.select(Exercise9.class).get();
                    exercise9.run(sc);
                    break;
                case "10":
                    Exercise10 exercise10 = container.select(Exercise10.class).get();
                    exercise10.run(sc);
                    break;
                case "11":
                    Exercise11 exercise11 = container.select(Exercise11.class).get();
                    exercise11.run(sc);
                    break;

                case "0":
                    System.out.println("Bye");
                    break;

                default:
                    System.out.println("Invalid option, try again");
                    break;
            }

        } while (!response.equals("0"));


    }
}
