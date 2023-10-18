package dao.impl;


import dao.CustomerDao;
import dao.db.DBConnection;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Customer;
import model.ErrorC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CustomerDaoImpl implements CustomerDao {

    private DBConnection dbConnection;

    @Inject
    public CustomerDaoImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Either<ErrorC, List<Customer>> getAll() {
        List<Customer> customers = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dbConnection.getConnection();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("select * from customer");
            customers.addAll(readRS(resultSet));
        } catch (SQLException e) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).severe(e.getMessage());
        } finally {
            dbConnection.closeConnection(connection);
        }
        return Either.right(customers);
    }

    private List<Customer> readRS(ResultSet resultSet) {
        List<Customer> customers = new ArrayList<>();
        try {
            while (resultSet.next()) {

                int customerID = resultSet.getInt("id");
                String customerName = resultSet.getString("name");
                String customerSurname = resultSet.getString("surname");
                LocalDate customerBirthDate;
                if (resultSet.getTimestamp("date_of_birth") != null) {
                    customerBirthDate = resultSet.getTimestamp("date_of_birth").toLocalDateTime().toLocalDate();
                } else {
                    customerBirthDate = null;
                }
                String customerEmail = resultSet.getString("email");
                int customerPhone = resultSet.getInt("phone");

                Customer customer = new Customer(customerID, customerName, customerSurname, customerEmail, customerPhone, customerBirthDate);

                customers.add(customer);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
