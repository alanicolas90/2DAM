package service;

import dao.OrderDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.ErrorC;
import model.Order;
import model.OrderItem;

import java.util.List;

public class OrderService {
    @Inject
    private OrderDao dao;

    public Either<ErrorC, List<Order>> getAll() {
        return dao.getAll();
    }

    public Either<Integer, Order> get(int id) {
        return dao.get(id);
    }

    public int save(Order t) {
        return 0;
    }

    public int update(Order t) {
        return 0;
    }

    public int delete(Order t) {
        return 0;
    }

    public Either<ErrorC, List<Order>> getOrdersCustomer(int id) {
        return dao.getOrdersCustomer(id);
    }

    public Either<ErrorC,List<OrderItem>> getOrderItems(int id) {
        return dao.getOrderItems(id);
    }
}
