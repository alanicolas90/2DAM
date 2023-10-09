package dao.impl;

import config.Configuration;
import dao.OrderDao;
import io.vavr.control.Either;
import model.ErrorC;
import model.Order;
import model.OrderItem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.*;

public class OrderDaoImpl implements OrderDao {

    public static final String ERROR_READING_FILE = "Error reading file";
    Path file = Paths.get(Configuration.getInstance().getPropertyTxt("txtOrdersFile"));


    @Override
    public Either<ErrorC, List<Order>> getAll() {

        List<Order> orders = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) continue;
                Order order = new Order(line);
                orders.add(order);
            }
        } catch (IOException x) {
            return Either.left(new ErrorC(ERROR_READING_FILE));
        }
        return Either.right(orders);
    }

    @Override
    public Either<ErrorC, List<OrderItem>> get(int id) {

        return null;
    }


    @Override
    public Either<ErrorC, Integer> save(Order order) {
        String line = order.toStringTextFile();

        try (BufferedWriter writer = Files.newBufferedWriter(file, APPEND)) {
            writer.newLine();
            writer.write(line, 0, line.length());

        } catch (IOException x) {
            return Either.left(new ErrorC(ERROR_READING_FILE));
        }
        return Either.right(0);
    }

    @Override
    public Either<ErrorC, Integer> update(Order c) {
        List<Order> allOrders = getAll().get();
        String orderLine = c.toStringTextFile();

        try (BufferedWriter writer = java.nio.file.Files.newBufferedWriter(file, TRUNCATE_EXISTING)) {
            for (Order allOrder : allOrders) {
                if (allOrder.getId() == c.getId()) {
                    writer.write(orderLine, 0, orderLine.length());
                    writer.newLine();
                } else {
                    String line = allOrder.toStringTextFile();
                    writer.write(line, 0, line.length());
                    writer.newLine();
                }
            }
        } catch (IOException x) {
            return Either.left(new ErrorC(ERROR_READING_FILE));
        }



        return Either.right(1);
    }

    @Override
    public Either<ErrorC, Integer> delete(List<Order> orderList) {
        List<Order> allOrders = getAll().get();

        String line;
        try (BufferedWriter writer = java.nio.file.Files.newBufferedWriter(file, TRUNCATE_EXISTING)) {
            for (Order allOrder : allOrders) {
                if (!orderList.contains(allOrder)) {
                    line = allOrder.toStringTextFile();
                    writer.write(line, 0, line.length());
                    writer.newLine();
                }
            }
        } catch (IOException x) {
            return Either.left(new ErrorC(ERROR_READING_FILE));
        }
        return Either.right(0);
    }
}

