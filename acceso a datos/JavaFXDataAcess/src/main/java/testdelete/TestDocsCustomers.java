package testdelete;

import config.Configuration;
import model.Customer;
import model.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class TestDocsCustomers {

    public static void main(String[] args) throws IOException {

        //extracted();
        LocalDateTime l = LocalDateTime.now();
        System.out.println(l);


    }

    private static void extracted() {
        Path file = Paths.get(Configuration.getInstance().getProperty("ordersFile"));

        try (BufferedReader reader = java.nio.file.Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!line.contains("id")) {
                    Order order = new Order();
                    order = order.parseToClass(line);
                    System.out.println(order.getId());
                    //System.out.println(customer);
                }
            }

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}
