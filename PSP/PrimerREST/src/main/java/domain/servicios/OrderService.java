package domain.servicios;

import dao.OrdersDao;
import dao.model.Order;
import domain.modelo.errores.NotFoundException;
import jakarta.inject.Inject;

import java.util.List;


public class OrderService {


    private final OrdersDao dao;


    @Inject
    public OrderService(OrdersDao dao) {
        this.dao = dao;
    }

    public List<Order> getAll() {
        List<Order> orders = dao.getAll();

        if (orders.isEmpty()) {
            throw new NotFoundException("No hay pedidos");
        } else {
            return orders;
        }
    }

    public Order get(int id) {
        Order order = dao.get(id);

        if(order == null) {
            throw new NotFoundException("No existe pedido con el id: " + id);
        }else{
            return order;
        }
    }

    public void add(Order order) {
        dao.add(order);
    }

    public void delete(Integer id) {
        dao.delete(id);
    }

    public void update(Order order) {
        int rowsAffected = dao.update(order);
        if(rowsAffected == 0) {
            throw new NotFoundException("No se ha podido actualizar el pedido, verifica si el pedido es el correcto");
        }
    }
}
