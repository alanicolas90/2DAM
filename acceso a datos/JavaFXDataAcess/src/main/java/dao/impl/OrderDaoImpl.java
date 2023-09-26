package dao.impl;

import dao.OrderDao;
import io.vavr.control.Either;
import model.ErrorC;
import model.Order;
import model.OrderItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public Either<ErrorC, List<Order>> getAll() {

        Either<ErrorC, List<Order>> result;

        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1, LocalDateTime.of(2023, 9, 22, 12, 0, 0, 0), 1, 1));
        orders.add(new Order(2, LocalDateTime.of(2023, 11, 10, 23, 0, 0, 0), 2, 1));
        orders.add(new Order(3, LocalDateTime.of(2023, 12, 30, 9, 0, 0, 0), 3, 1));

        result = Either.right(orders);
        return result;
    }

    @Override
    public Either<ErrorC, List<OrderItem>> get(int id) {


        return null;
    }

    @Override
    public Either<ErrorC, List<Order>> getOrdersCustomer(int id) {
        List<Order> allOrders = getAll().get();
        List<Order> customerOrders = new ArrayList<>();
        Either<ErrorC, List<Order>> result;
        for (Order order : allOrders) {
            if (order.getCustomerId() == id) {
                customerOrders.add(order);
            }
        }
        result = Either.right(customerOrders);
        return result;
    }

    @Override
    public Either<ErrorC, List<OrderItem>> getAllOrderItems() {
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(1, 1, 1, 1));
        orderItems.add(new OrderItem(2, 2, 1, 2));
        orderItems.add(new OrderItem(3, 3, 2, 1));
        Either<ErrorC, List<OrderItem>> result;
        result = Either.right(orderItems);
        return result;
    }

    @Override
    public Either<ErrorC, List<OrderItem>> getOrderItems(int id) {
        List<OrderItem> orderItems = getAllOrderItems().get();
        Either<ErrorC, List<OrderItem>> result;
        result = Either.right(orderItems.stream().filter(o -> o.getOrderId() == id).toList());
        return result;
    }
}
