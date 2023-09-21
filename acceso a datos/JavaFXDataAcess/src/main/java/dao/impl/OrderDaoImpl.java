package dao.impl;

import dao.OrderDao;
import io.vavr.control.Either;
import model.Order;

import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public Either<Integer, List<Order>> getAll() {
        return null;
    }

    @Override
    public Either<Integer, Order> get(int id) {
        return null;
    }
}
