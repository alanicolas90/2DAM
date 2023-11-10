package domain.servicios;

import dao.OrdersDao;
import dao.model.Order;
import jakarta.inject.Inject;

import java.util.List;


public class OrderService {


    private OrdersDao dao;


    @Inject
    public OrderService(OrdersDao dao) {
        this.dao = dao;
    }

    public List<Order> getAll()
    {
        return dao.getAll();
    }

    public List<Order> get(int id) {
        return dao.get(id);
    }

    public void add(Order order) {
        dao.add(order);
    }

}
