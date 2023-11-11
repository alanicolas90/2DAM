package domain.servicios;

import dao.CustomerDao;
import dao.OrdersDao;
import dao.model.Order;
import domain.modelo.errores.ModificacionException;
import domain.modelo.errores.NotFoundException;
import domain.utils.ConstantesDomain;
import jakarta.inject.Inject;

import java.util.List;


public class OrderService {


    private final OrdersDao dao;
    private final CustomerDao customerDao;


    @Inject
    public OrderService(OrdersDao dao,CustomerDao customerDao) {
        this.dao = dao;
        this.customerDao = customerDao;
    }

    public List<Order> getAll() {
        List<Order> orders = dao.getAll();

        if (orders.isEmpty()) {
            throw new NotFoundException(ConstantesDomain.NO_HAY_PEDIDOS);
        } else {
            return orders;
        }
    }

    public Order get(int id) {
        Order order = dao.get(id);

        if(order == null) {
            throw new NotFoundException(ConstantesDomain.NO_EXISTE_PEDIDO_CON_EL_ID + id);
        }else{
            return order;
        }
    }

    public void add(Order order) {
        if(customerDao.get(order.getCustomerId()) == null) {
            throw new NotFoundException(ConstantesDomain.NO_EXISTE_CLIENTE_CON_ID + order.getCustomerId());
        }
        int rowsAffected = dao.add(order);
        if(rowsAffected == 0) {
            throw new ModificacionException(ConstantesDomain.NO_SE_HA_PODIDO_ADD_EL_PEDIDO_VERIFICA_SI_EL_ID_DEL_PEDIDO_ES_EL_CORRECTO);
        }
    }

    public void delete(Integer id) {
        int rowsAffected = dao.delete(id);
        if(rowsAffected == 0) {
            throw new ModificacionException(ConstantesDomain.NO_SE_HA_PODIDO_ELIMINAR_EL_PEDIDO_VERIFICA_SI_EL_PEDIDO_ES_EL_CORRECTO);
        }
    }

    public void update(Order order) {
        int rowsAffected = dao.update(order);
        if(rowsAffected == 0) {
            throw new ModificacionException(ConstantesDomain.NO_SE_HA_PODIDO_ACTUALIZAR_EL_PEDIDO_VERIFICA_SI_EL_PEDIDO_ES_EL_CORRECTO);
        }
    }
}
