package dao.impl;


import dao.CustomerDao;
import dao.db.DBConnection;
import dao.utils.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.ErrorC;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Log4j2
public class CustomerDaoImpl implements CustomerDao {

    private final DBConnection dbConnection;

    @Inject
    public CustomerDaoImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Either<ErrorC, Customer> getCustomerById(int id) {
        Customer customer = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = dbConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQLQueries.GET_CUSTOMER_SPECIFIC_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            customer = readRS(resultSet).get(0);
        }catch(Exception e){
            log.error(e.getMessage());
        }finally {
            dbConnection.closeConnection(connection);
            dbConnection.releaseResource(preparedStatement);
        }

        if(customer == null){
            return Either.left(new ErrorC("Customer not found"));
        }else{
            return Either.right(customer);
        }
    }

    @Override
    public Either<ErrorC, Integer> update(Customer customerUpdated) {
        Either<ErrorC, Integer> result;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = dbConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQLQueries.UPDATE_CUSTOMER);
            preparedStatement.setString(1, customerUpdated.getName());
            preparedStatement.setString(2, customerUpdated.getSurname());
            preparedStatement.setString(3, customerUpdated.getEmail());
            preparedStatement.setInt(4, customerUpdated.getPhone());
            if(customerUpdated.getBirthDate() == null){
                preparedStatement.setNull(5, Types.DATE);
            }else{
                preparedStatement.setDate(5, Date.valueOf(customerUpdated.getBirthDate()));
            }
            preparedStatement.setInt(6, customerUpdated.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            result = Either.right(rowsAffected);
            return result;

        }catch(SQLException e){
            log.error(e.getMessage());
        }finally {
            dbConnection.closeConnection(connection);
            dbConnection.releaseResource(preparedStatement);
        }
        return null;
    }

    @Override
    public Either<ErrorC, List<Customer>> getAll() {
        List<Customer> customers = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dbConnection.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQLQueries.GET_ALL_CUSTOMERS);
            customers.addAll(readRS(resultSet));
        } catch (SQLException e) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).severe(e.getMessage());
        } finally {
            dbConnection.closeConnection(connection);
            dbConnection.releaseResource(statement);
        }
        return Either.right(customers);
    }

    @Override
    public int saveAutoIncrementalID(String name, String surname, String email, int phone, LocalDate dateOfBirth) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        int rowsAffected = 0;
        try {
            connection = dbConnection.getConnection();

            preparedStatement1 = connection.prepareStatement("insert into credentials (user_name,password) values (?,?)");
            preparedStatement1.setString(1, name);
            preparedStatement1.setString(2, surname);
            preparedStatement1.executeUpdate();

            int lastId = getLastId(connection);

            preparedStatement = connection.prepareStatement("INSERT INTO customers (`id`, `first_name`, `last_name`, `email`, `phone`, `date_of_birth`) VALUES (?, ?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, lastId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, surname);
            preparedStatement.setString(4, email);
            preparedStatement.setInt(5, phone);
            if (dateOfBirth == null) {
                preparedStatement.setNull(6, Types.DATE);
            } else {
                preparedStatement.setDate(6, Date.valueOf(dateOfBirth));
            }
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            dbConnection.closeConnection(connection);
            dbConnection.releaseResource(preparedStatement);
        }
        return rowsAffected;
    }

    @Override
    public int delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;
        try {
            connection = dbConnection.getConnection();
            preparedStatement = connection.prepareStatement("delete from customers where id = ?");
            preparedStatement.setInt(1, id);
            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            dbConnection.closeConnection(connection);
            dbConnection.releaseResource(preparedStatement);
        }
        return rowsAffected;
    }

    private Either<ErrorC, Integer> delete(int id, boolean confirm){
        Connection connection;
        PreparedStatement preparedStatement;
        int rowsAffected = 0;
        if(!confirm){
            try{
                connection = dbConnection.getConnection();
                preparedStatement = connection.prepareStatement("delete from customers where id = ?");
                preparedStatement.setInt(1, id);
                rowsAffected = preparedStatement.executeUpdate();
            }catch(SQLException e){
                if(e.getErrorCode() == 1451){
                    return Either.left(new ErrorC("Customer has orders, are you sure you want to delete it?"));
                }else{
                    log.error(e.getMessage());
                }
            }
        }
        return Either.right(rowsAffected);
    }

    private int getLastId(Connection connection) {
        Statement statement = null;
        int result = 0;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id from credentials order by id desc limit 1");
            resultSet.next();
            result = resultSet.getInt("id");
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            dbConnection.releaseResource(statement);
        }
        return result;
    }


    private List<Customer> readRS(ResultSet resultSet) {
        List<Customer> customers = new ArrayList<>();
        try {
            while (resultSet.next()) {

                int customerID = resultSet.getInt("id");
                String customerName = resultSet.getString("first_name");
                String customerSurname = resultSet.getString("last_name");
                String customerEmail = resultSet.getString("email");
                int customerPhone = resultSet.getInt("phone");
                LocalDate customerBirthDate;
                if (resultSet.getTimestamp("date_of_birth") != null) {
                    customerBirthDate = resultSet.getTimestamp("date_of_birth").toLocalDateTime().toLocalDate();
                } else {
                    customerBirthDate = null;
                }
                Customer customer = new Customer(customerID, customerName, customerSurname,customerEmail,customerPhone,customerBirthDate);
                customers.add(customer);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(e.getStackTrace());
        }
        return customers;
    }
}
