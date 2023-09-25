package dao;

import io.vavr.control.Either;
import model.ErrorC;
import model.Order;
import model.OrderItem;

import java.util.List;

public interface OrderDao {
    Either<ErrorC, List<Order>> getAll();

    Either<ErrorC, List<OrderItem>> get(int id);

    Either<ErrorC, List<Order>> getOrdersCustomer(int id);

    Either<ErrorC, List<OrderItem>> getOrderItems(int id);

    Either<ErrorC, List<OrderItem>> getAllOrderItems();
}
