package dao;

import dao.model.Customer;
import dao.model.Order;

import java.util.List;

public interface CustomerDao {

    List<Customer> getAll();

    Integer save(Customer customer);

    Customer get(int id);

    Integer update(Customer customerUpdated);

    Integer delete(int id);

    List<Order> customerHasOrders(Integer id);
}
