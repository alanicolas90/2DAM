package service;

import dao.OrderDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.ErrorC;
import model.Order;
import model.OrderItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public static final String NO_ORDERS_FOUND = "No orders found";
    private final OrderDao daoOrders;

    @Inject
    public OrderService(OrderDao daoOrders) {
        this.daoOrders = daoOrders;
    }

    public Either<ErrorC, List<Order>> getAll() {
        return daoOrders.getAll();
    }

    public Either<ErrorC, List<OrderItem>> get(int id) {
        return daoOrders.get(id);
    }

    public Either<ErrorC, List<Order>> getOrdersCustomer(int id) {
        List<Order> allOrders = daoOrders.getAll().get();
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : allOrders) {
            if (order.getCustomerId() == id) {
                customerOrders.add(order);
            }
        }
        if (customerOrders.isEmpty()) {
            return Either.left(new ErrorC(NO_ORDERS_FOUND));
        } else {
            return Either.right(customerOrders);
        }
    }

    public Either<ErrorC, Integer> save(Order o) {
        return daoOrders.save(o);
    }

    public Either<ErrorC, Integer> update(Order o) {
        return daoOrders.update(o);
    }

    public Either<ErrorC, Integer> delete(List<Order> orderList) {
        return daoOrders.delete(orderList);
    }


    public Either<ErrorC, List<Order>> getOrdersByDate(LocalDate value) {
        List<Order> allOrders = daoOrders.getAll().get();
        List<Order> ordersContainDate = new ArrayList<>();

        for (Order order : allOrders) {
            if (order.getDate().toLocalDate().getDayOfYear() == value.getDayOfYear() && order.getDate().toLocalDate().getYear() == value.getYear()) {
                ordersContainDate.add(order);
            }
        }
        if (ordersContainDate.isEmpty()) {
            return Either.left(new ErrorC(NO_ORDERS_FOUND));
        } else {
            return Either.right(ordersContainDate);
        }
    }
}
