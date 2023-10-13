package dao;

import io.vavr.control.Either;
import model.ErrorC;
import model.Order;
import model.OrderItem;
import model.xml.OrderItemXml;
import model.xml.OrdersXml;

import java.util.List;

public interface OrderItemsDao {

    Either<ErrorC, OrdersXml> getAll();

    Either<ErrorC, List<OrderItemXml>> get(int id);

    Either<ErrorC, Integer> save(Order c);
    Either<ErrorC, Integer> update(Order c);
    Either<ErrorC, Integer> delete(int idOrder);
}
