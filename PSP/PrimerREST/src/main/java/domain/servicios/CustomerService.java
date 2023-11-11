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
        return dao.getAll();
    }

    public Customer get(int id) {
        return dao.get(id);
    }

    public Customer add(Customer c) {
        dao.save(c);
        return c;
    }

    public void delete(Integer id) {
        dao.delete(id);
    }

    public void update(Customer customer) {
        dao.update(customer);
    }
}
