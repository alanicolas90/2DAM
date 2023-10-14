package service;

import config.common.Constants;
import dao.CustomerDao;
import dao.OrderDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Customer;
import model.ErrorC;
import model.Order;

import java.util.List;

public class CustomerService {

    private final CustomerDao dao;
    private final OrderDao orderDao;

    @Inject
    public CustomerService(CustomerDao dao, OrderDao orderDao) {
        this.dao = dao;
        this.orderDao = orderDao;
    }

    public Either<ErrorC, List<Customer>> getAll() {
        return dao.getAll();
    }

    public Either<ErrorC, List<Integer>> getAllIds() {
        List<Customer> customers = dao.getAll().get();
        List<Integer> ids = customers.stream().map(Customer::getId).toList();
        if (ids.isEmpty()) {
            return Either.left(new ErrorC(Constants.CUSTOMER_NOT_FOUND));
        } else {
            return Either.right(ids);
        }
    }

    public Either<ErrorC, Customer> get(int id) {
        List<Customer> customers = dao.getAll().get();

        if (customers.stream().noneMatch(c -> c.getId() == id)) {
            return Either.left(new ErrorC(Constants.CUSTOMER_NOT_FOUND));
        } else {
            return Either.right(customers.stream().filter(c -> c.getId() == id).findFirst().stream().toList().get(0));
        }
    }

    public Either<ErrorC, Integer> delete(Customer c) {
        if (dao.getAll().get().stream().noneMatch(customer -> customer.getId() == c.getId())
                && orderDao.getAll().get().stream().noneMatch(order -> order.getCustomerId() == c.getId())) {

            return Either.left(new ErrorC(Constants.CUSTOMER_NOT_FOUND));
        } else {
            List<Order> ordersCustomer = orderDao.getAll().get().stream().filter(order -> order.getCustomerId() == c.getId()).toList();
            List<Integer> listIdsOrder= ordersCustomer.stream().map(Order::getId).toList();
            orderDao.delete(listIdsOrder);
            dao.delete(c);
            return Either.right(0);
        }
    }

    public Either<ErrorC, Integer> save(Customer c) {
        return dao.save(c);
    }

    public Either<ErrorC, Integer> update(Customer c) {
        return dao.update(c);
    }



}
