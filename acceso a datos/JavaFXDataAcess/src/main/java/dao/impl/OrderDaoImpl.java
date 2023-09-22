package dao.impl;

import dao.OrderDao;
import io.vavr.control.Either;
import model.ErrorC;
import model.Order;

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
    public Either<Integer, Order> get(int id) {
        return null;
    }
}
