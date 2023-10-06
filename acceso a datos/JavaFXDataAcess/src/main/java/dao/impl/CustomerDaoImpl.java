package dao.impl;

import config.Configuration;
import dao.CustomerDao;
import io.vavr.control.Either;
import model.Customer;
import model.ErrorC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    Path file = Paths.get(Configuration.getInstance().getProperty("customersFile"));

    @Override
    public Either<ErrorC, List<Customer>> getAll() {

        List<Customer> customerList = new ArrayList<>();
        List<String> fileList;
        try {
            fileList = Files.readAllLines(file);
            fileList.forEach(line -> {
                if (!line.isEmpty()) {
                    Customer customerResult = new Customer(line);
                    customerList.add(customerResult);
                }
            });
        } catch (IOException e) {
            return Either.left(new ErrorC("Error reading file"));
        }
        return Either.right(customerList);
    }


    @Override
    public Either<ErrorC, Integer> save(Customer c) {
        return Either.right(1);
    }

    @Override
    public Either<ErrorC, Integer> update(Customer c) {
        return Either.right(1);
    }

    @Override
    public Either<ErrorC, Integer> delete(Customer c) {
        List<Customer> allCustomers = getAll().get();
        String customerString = c.toStringTextFile();
        List<String> fileList = new ArrayList<>();

        for (Customer allCustomer : allCustomers) {
            if (!allCustomer.toStringTextFile().equals(customerString)) {
                fileList.add(allCustomer.toStringTextFile());
            }
        }
        try {
            Files.write(file, fileList, java.nio.file.StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            return Either.left(new ErrorC("Error reading file"));
        }
        return Either.right(0);
    }


}
