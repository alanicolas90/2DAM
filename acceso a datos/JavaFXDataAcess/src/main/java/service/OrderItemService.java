package service;

import dao.OrderItemsDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.ErrorC;
import model.xml.OrderItemXml;
import model.xml.OrderXml;
import model.xml.OrdersXml;

import java.util.List;

public class OrderItemService {

    private final OrderItemsDao orderItemsDao;

    @Inject
    public OrderItemService(OrderItemsDao orderItemsDao) {
        this.orderItemsDao = orderItemsDao;
    }


    public Either<ErrorC, OrdersXml> getAll() {
        return Either.right(orderItemsDao.getAll().get());
    }

    public Either<ErrorC, List<OrderItemXml>> get(int id) {
        if (orderItemsDao.get(id).isLeft()) {
            return Either.left(orderItemsDao.get(id).getLeft());
        }
        return Either.right(orderItemsDao.get(id).get());
    }

    public Either<ErrorC, Integer> delete(int idOrder) {

        return orderItemsDao.delete(idOrder);
    }

    public Either<ErrorC, Integer> save(int idOrder,OrderItemXml c) {
        return orderItemsDao.save(idOrder,c);
    }
}
