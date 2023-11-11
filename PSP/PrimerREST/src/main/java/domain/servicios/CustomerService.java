package domain.servicios;

import dao.CustomerDao;
import dao.model.Customer;
import domain.modelo.errores.ModificacionException;
import domain.modelo.errores.NotFoundException;
import domain.utils.ConstantesDomain;
import jakarta.inject.Inject;

import java.util.List;

public class CustomerService {

    private final CustomerDao dao;


    @Inject
    public CustomerService(CustomerDao dao) {
        this.dao = dao;
    }

    public List<Customer> getAll() {
        List<Customer> customers = dao.getAll();

        if(customers.isEmpty()) {
            throw new NotFoundException(ConstantesDomain.NO_HAY_CLIENTES);
        }else{
            return customers;
        }
    }

    public Customer get(int id) {
        Customer customer = dao.get(id);

        if(customer == null) {
            throw new NotFoundException(ConstantesDomain.NO_EXISTE_EL_CLIENTE_CON_ID + id+ ConstantesDomain.PARENTESIS_CIERRE);
        }else{
            return customer;
        }
    }

    public void add(Customer c) {
        int rowsAffected = dao.save(c);

        if(rowsAffected == 0) {
            throw new ModificacionException(ConstantesDomain.ERROR_ADD_CLIENTE);
        }
    }

    public void delete(Integer id) {
        int rowsAffected = dao.delete(id);
        if (rowsAffected == 0) {
            throw new ModificacionException(ConstantesDomain.NO_SE_HA_PODIDO_ELIMINAR_EL_CLIENTE);
        }
    }

    public void update(Customer customer) {
        int rowsAffected = dao.update(customer);
        if(rowsAffected == 0) {
            throw new ModificacionException(ConstantesDomain.NO_SE_HA_PODIDO_ACTUALIZAR_EL_CLIENTE);
        }
    }
}
