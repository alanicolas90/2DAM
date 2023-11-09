package domain.servicios;

import dao.CustomerDao;
import dao.model.Customer;
import jakarta.inject.Inject;

import java.util.List;

public class ServiciosBuenosUsuarios {

    private CustomerDao dao;

    @Inject
    public ServiciosBuenosUsuarios(CustomerDao dao) {
        this.dao = dao;
    }

    public List<Customer> dameTodos() {
        return dao.getAll().get();
    }

    public Customer dameUsuario(String id) {

        return dao.get(Integer.parseInt(id)).get();
    }
}
