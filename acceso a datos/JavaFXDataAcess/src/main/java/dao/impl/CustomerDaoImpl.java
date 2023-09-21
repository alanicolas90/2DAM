package dao.impl;

import dao.CustomerDao;
import io.vavr.control.Either;
import model.Customer;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public Either<Integer, List<Customer>> getAll() {
        return null;
    }

    @Override
    public Either<Integer, Customer> get(int id) {
        return null;
    }
}
