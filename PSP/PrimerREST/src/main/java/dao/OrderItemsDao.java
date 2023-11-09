package dao;

import dao.model.ErrorC;
import dao.model.OrderItem;
import io.vavr.control.Either;

import java.util.List;

public interface OrderItemsDao {

    Either<ErrorC, List<OrderItem>> get(int orderId);
}
