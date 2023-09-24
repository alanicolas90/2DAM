package service;

import dao.OrderDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.ErrorC;
import model.Order;

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

    public Either<ErrorC, List<Order>> getOrdersCustomer(int id) {
        return dao.getOrdersCustomer(id);
    }

}
