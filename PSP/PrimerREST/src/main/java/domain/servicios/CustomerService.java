package domain.servicios;

import dao.CustomerDao;
import dao.model.Customer;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private CustomerDao dao;


    @Inject
    public CustomerService(CustomerDao dao) {
        this.dao = dao;
    }

    public List<Customer> getAll() {
        return dao.getAll();
    }

    public Customer get(String id) {
        return dao.get(Integer.parseInt(id)).get();
    }

    public Customer add(Customer c) {
        dao.saveAutoIncrementalID(c);
        return c;
    }

    public void delete(Integer id) {
        dao.delete(id);
    }
}
