package dao;

import io.vavr.control.Either;
import model.ErrorC;
import model.Order;

import java.util.List;

public interface OrdersDao {

    Either<ErrorC, List<Order>> getAll();

}
