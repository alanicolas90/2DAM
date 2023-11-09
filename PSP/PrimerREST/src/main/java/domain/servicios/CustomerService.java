package domain.servicios;

import dao.CustomerDao;
import dao.model.Customer;
import jakarta.inject.Inject;

import java.util.List;

public class CustomerService {

    private CustomerDao dao;

    @Inject
    public CustomerService(CustomerDao dao) {
        this.dao = dao;
    }

    public List<Customer> getAll() {
        return dao.getAll().get();
    }

    public Customer get(String id) {

        return dao.get(Integer.parseInt(id)).get();
    }
}
