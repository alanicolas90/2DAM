package service;

import dao.OrdersDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.ErrorC;
import model.Order;

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
        if (ordersDao.getAll().isLeft()) {
            return Either.left(new ErrorC("No orders found"));
        } else {
            return ordersDao.getAll();
        }
    }

    public Either<ErrorC, List<Order>> getOrdersSpecificDate(LocalDate dateSelected) {
        List<Order> orders = ordersDao.getAll().get();
        List<Order> ordersFiltered = orders.stream().filter(order -> {
            LocalDate date = LocalDate.from(order.getDate());
            return date.equals(dateSelected);
        }).toList();
        if (ordersFiltered.isEmpty()) {
            return Either.left(new ErrorC("No orders found"));
        } else {
            return Either.right(ordersFiltered);
        }
    }

    public Either<ErrorC, List<Order>> getOrdersSpecificCustomer(int customerId) {
        List<Order> orders = ordersDao.getAll().get();
        List<Order> ordersFiltered = orders.stream().filter(order -> order.getCustomerId() == customerId).toList();

        if (ordersFiltered.isEmpty()) {
            return Either.left(new ErrorC("No orders found"));
        } else {
            return Either.right(ordersFiltered);
        }
    }

    public Either<ErrorC, List<Order>> get(int idUserLogged) {
        if(ordersDao.get(idUserLogged).isLeft()){
            return Either.left(new ErrorC("No orders found"));
        }else{
            return ordersDao.get(idUserLogged);
        }
    }

    public Either<ErrorC, Integer> add(LocalDateTime date,int customerId,int tableNumber) {
        return ordersDao.add(date, customerId, tableNumber);
    }

    public Either<ErrorC, Integer> update(LocalDateTime date,int tableNumber,int orderID) {
        Either<ErrorC,Integer> eitherUpdate = ordersDao.update(date,tableNumber, orderID);

        return eitherUpdate;

    }

    public Either<ErrorC, Integer> delete(int id) {
        Either<ErrorC, Integer> eitherDelete = ordersDao.delete(id);
        if(eitherDelete.isLeft()){
            return Either.left(new ErrorC("No order found"));
        }else{
            return eitherDelete;
        }
    }
}
