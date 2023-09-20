package service;

import dao.OrderDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Order;

import java.util.List;

public class OrderService {
    @Inject
    private OrderDao dao;

    public Either<Integer, List<Order>> getAll() {
        return dao.getAll();
    }

    public Either<Integer, Order> get(int id) {
        return dao.get(id);
    }

    public int save(Order t) { return 0; }

    public int update(Order t) {return 0;}

    public int delete(Order t) {return 0;}
}
