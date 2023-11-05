package service;

import dao.OrderItemsDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.ErrorC;
import model.OrderItem;

import java.util.List;

public class OrderItemsService {

    private final OrderItemsDao orderItemsDao;

    @Inject
    public OrderItemsService(OrderItemsDao orderItemsDao) {
        this.orderItemsDao = orderItemsDao;
    }

    public Either<ErrorC, List<OrderItem>> get(int orderId){
        return orderItemsDao.get(orderId);
    }

}
