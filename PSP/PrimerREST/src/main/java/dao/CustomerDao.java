package dao;

import dao.model.Customer;

import java.util.List;

public interface CustomerDao {

    List<Customer> getAll();

    Integer save(Customer customer);

    Customer get(int id);

    Integer update(Customer customerUpdated);

    Integer delete(int id);
}
