package service;

import dao.OrderDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.ErrorC;
import model.Order;
import model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final OrderDao dao;

    @Inject
    public OrderService(OrderDao dao) {
        this.dao = dao;
    }

    public Either<ErrorC, List<Order>> getAll() {
        return dao.getAll();
    }

    public Either<ErrorC, List<OrderItem>> get(int id) {
        return dao.get(id);
    }

    public Either<ErrorC, List<Order>> getOrdersCustomer(int id) {
        List<Order> allOrders = dao.getAll().get();
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : allOrders) {
            if (order.getCustomerId() == id) {
                customerOrders.add(order);
            }
        }
        if(customerOrders.isEmpty()) {
            return Either.left(new ErrorC("No orders found"));
        }else {
            return Either.right(customerOrders);
        }
    }

    public Either<ErrorC, Integer> save(Order o) {
        return dao.save(o);
    }

    public Either<ErrorC, Integer> update(Order o) {
        return dao.update(o);
    }

    public Either<ErrorC, Integer> delete(List<Order> orderList) {
        return dao.delete(orderList);
    }


}
