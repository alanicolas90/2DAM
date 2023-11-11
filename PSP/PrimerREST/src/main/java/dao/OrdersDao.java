package dao;

import dao.model.Order;

import java.util.List;

public interface OrdersDao {

    List<Order> getAll();

    Order get(int idUserLogged);

    Integer add(Order order);

    Integer update(Order order);

    Integer delete(int id);
}
