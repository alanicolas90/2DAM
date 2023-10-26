package service;

import dao.OrdersDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.ErrorC;
import model.Order;
import service.utils.ServiceConstants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OrdersService {

    private final OrdersDao ordersDao;

    @Inject
    public OrdersService(OrdersDao ordersDao) {
        this.ordersDao = ordersDao;
    }

    public Either<ErrorC, List<Order>> getAll() {
        return ordersDao.getAll();
    }

    public Either<ErrorC, List<Order>> getOrdersSpecificDate(LocalDate dateSelected) {
        List<Order> orders = ordersDao.getAll().get();
        List<Order> ordersFiltered = orders.stream().filter(order -> {
            LocalDate date = LocalDate.from(order.getDate());
            return date.equals(dateSelected);
        }).toList();
        if (ordersFiltered.isEmpty()) {
            return Either.left(new ErrorC(ServiceConstants.NO_ORDERS_FOUND));
        } else {
            return Either.right(ordersFiltered);
        }
    }

    public Either<ErrorC, List<Order>> get(int idUserLogged) {
        return ordersDao.get(idUserLogged);
    }

    public Either<ErrorC, Integer> add(Order order) {
        return ordersDao.add(order);
    }

    public Either<ErrorC, Integer> update(Order order) {
        return ordersDao.update(order);
    }

    public Either<ErrorC, Integer> delete(int id,boolean delete) {
        return ordersDao.delete(id , delete);
    }
}
